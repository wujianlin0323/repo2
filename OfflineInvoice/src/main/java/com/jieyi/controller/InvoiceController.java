package com.jieyi.controller;

import com.jieyi.configure.ActiveMQConfig;
import com.jieyi.model.fpInfo;
import com.jieyi.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.jieyi.entity.invoiceInfo;
import com.jieyi.service.ActiveMQProducer;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import com.jieyi.mapper.GoodInfoMapper;
import com.jieyi.mapper.FPInfoMapper;
import com.jieyi.entity.t_bse_info_goods;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@Configuration
public class InvoiceController {
    private Logger logger = LoggerFactory.getLogger(InvoiceController.class);

    private final static DecimalFormat fmt = new DecimalFormat("##,###,###,###,##0.00");

    @Value("${appKey}")
    private String appKey;
    @Value("${kp.rct.name}")
    private String xhf_name;
    @Value("${kp.rct.taxno}")
    private String xhf_taxno;
    @Value("${kp.rct.addressAndtel}")
    private String xhf_addressAndtel;

    @Value("${kp.rct.bank}")
    private String xhf_bank;

    @Value("${kp.hx.webservice_url_kp}")
    private String webservice_url_kp;

    @Value("${kp.hx.webservice_url_qz}")
    private String webservice_url_qz;

    @Value("${kp.hx.platform_erpcode}")
    private String platform_erpcode;
    @Value("${kp.hx.platform_authorizationcode}")
    private String platform_authorizationcode;
    @Value("${kp.hx.platform_registrationcode}")
    private String platform_registrationcode;

    @Value("${kp.pdf.path}")
    private String pdf_path;

    @Value("${kp.rct.kpy}")
    private String kpy;

    @Autowired
    private GoodInfoMapper goodInfoMapper;

    @Autowired
    private FPInfoMapper fpInfoMapper;


    @Autowired
    private ActiveMQProducer activeMQProducer;

    @RequestMapping(value = "/invoice", method = RequestMethod.POST)
    @ResponseBody
    public Object invoice(ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> map = new HashMap<>();
        String headtype = request.getParameter("headtype");
        String headname = request.getParameter("headname");
        String taxno = request.getParameter("taxno");
        String mobile = request.getParameter("mobile");
        String email = request.getParameter("email");
        String tel = request.getParameter("tel");
        String address = request.getParameter("address");
        String bankname = request.getParameter("bankname");
        String accountno = request.getParameter("accountno");
        String id = request.getParameter("id");


        if (id == null) {
            map.put("data", "无效链接");
            return map;
        }

        fpInfo fpinfo = new fpInfo();
        try {
            String decryptStr = SecurityHelper.decrypt(appKey, request.getParameter("id"));
            Map<String, String> mapParam = URLParamParse.URLParamParse(decryptStr);

            String cardno = mapParam.get("cardno");
            String ref_key = mapParam.get("key");
            fpinfo.setCardno(cardno);
            fpinfo.setRef_key(ref_key);
        } catch (Exception e) {
            map.put("data", e.getMessage());
            return map;
        }
        fpinfo.setTxn_src("10");/*客服*/
        fpinfo.setStatus("0");
        fpinfo.setFp_status("0");
        fpInfo fi = fpInfoMapper.selectFPInfo(fpinfo.getCardno(), fpinfo.getRef_key());
        if (fi != null) {
            try {
                FPQZService.FPQZ(fi, webservice_url_qz, platform_registrationcode, platform_authorizationcode, platform_erpcode, pdf_path);
                fpInfoMapper.updateFPInfo(fi);//update status=1
                //logger.info("");
                fpInfoMapper.updateFpTxnInfo(fi);//update fp_status=1
                fpinfo.setFpqqlsh(fi.getFpqqlsh());
                fpinfo.setPdf_url(fi.getPdf_url());
                //更新充值记录表
                fpInfoMapper.updateCardsvInfo(fi.getCardno(), fi.getRef_key(), fi.getFp_hm(), fi.getFp_dm());
            } catch (Exception e) {
                map.put("data", e.getMessage());
                return map;
            }
        } else {
            fpInfo txninfo = fpInfoMapper.selectTxnData(fpinfo.getCardno(), fpinfo.getRef_key());
            if (txninfo == null) {
                map.put("data", "原交易不存在");
                return map;
            }
            fpinfo.setTxnamt(txninfo.getTxnamt());
            fpinfo.setInntype(txninfo.getInntype());
            fpinfo.setTxndate(txninfo.getTxndate());
            t_bse_info_goods goodsinfo = goodInfoMapper.selectByInntype(txninfo.getInntype());
            if (goodsinfo == null) {
                map.put("data", "该商品不支持开票");
                return map;
            }
            fpinfo.setSpbm(goodsinfo.getGoods_id());
            fpinfo.setSpmc(goodsinfo.getGoods_name());
            fpinfo.setSm(goodsinfo.getTax_name());
            fpinfo.setJldw(goodsinfo.getCal_unit());
            fpinfo.setGgxh(goodsinfo.getModel());
            fpinfo.setHsbz(goodsinfo.getTax_sign());
            fpinfo.setSl(goodsinfo.getTax_rate());
            fpinfo.setGhfqylx(request.getParameter("headtype"));

            fpinfo.setGhfmc(request.getParameter("headname"));
            if (!request.getParameter("taxno").equals("")) {
                fpinfo.setGhf_nsrsbh(request.getParameter("taxno"));
            }
            fpinfo.setGhf_dz(request.getParameter("address"));
            fpinfo.setGhf_gddh(request.getParameter("tel"));

            fpinfo.setGhf_sj(request.getParameter("mobile"));
            fpinfo.setGhf_email(request.getParameter("email"));
            if (!request.getParameter("bankname").equals("") && !request.getParameter("accountno").equals("")) {
                fpinfo.setGhf_yhzh(request.getParameter("bankname") + " " + request.getParameter("accountno"));
            }
            if (!request.getParameter("address").equals("") && !request.getParameter("tel").equals("")) {
                fpinfo.setGhf_dzdh(request.getParameter("address") + " " + request.getParameter("tel"));
            }

            if (!request.getParameter("mobile").equals("") && !request.getParameter("email").equals("")) {
                //邮箱，手机都推
                fpinfo.setTsfs("2");
            } else if (!request.getParameter("mobile").equals("")) {
                //手机推送
                fpinfo.setTsfs("0");
            } else if (!request.getParameter("email").equals("")) {
                //邮箱推送
                fpinfo.setTsfs("1");
            }
            fpinfo.setXhf_nsrsbh(xhf_taxno);
            fpinfo.setXhf_yhzh(xhf_bank);
            fpinfo.setXhfmc(xhf_name);
            fpinfo.setXhf_dzdh(xhf_addressAndtel);
            fpinfo.setKpy(kpy);
            fpinfo.setFpqqlsh(SeqUtil.getFPQQLSH());
            fpinfo.setKplx("0");
            fpinfo.setKphjje(fmt.format(Double.parseDouble(fpinfo.getTxnamt()) / 100));
            fpinfo.setQd_bz("0");
            // fpinfo.setHjse(fmt.format((Double.parseDouble(fpinfo.getTxnamt()) /(1+Double.parseDouble(goodsinfo.getTax_rate()))) / 100));

            fpinfo.setHjse(fmt.format((Double.parseDouble(fpinfo.getTxnamt()) - Double.parseDouble(fpinfo.getTxnamt()) / (1 + Double.parseDouble(goodsinfo.getTax_rate()))) / 100));

            fpinfo.setHjbhsje(fmt.format(Double.parseDouble(fpinfo.getTxnamt()) / (1 + Double.parseDouble(goodsinfo.getTax_rate())) / 100));


            fpinfo.setFphxz("0");
            fpinfo.setYhzcbs("0");
            fpinfo.setSpsl("1");

            fpinfo.setSpje(fpinfo.getHjbhsje());
            fpinfo.setSpdj(fpinfo.getHjbhsje());
            //fpinfo.setSpje(fpinfo.getKphjje());
            //fpinfo.setSpdj(fpinfo.getKphjje());
            fpinfo.setSe(fpinfo.getHjse());
            fpinfo.setSjly("06");
            fpinfo.setFplx("1");
            fpinfo.setDkbz("0");
            try {
                FPKPService.FPKJ(fpinfo, webservice_url_kp);
            } catch (Exception e) {
                map.put("data", e.getMessage());
                return map;
            }

            logger.info("getReturnCode=" + fpinfo.getReturnCode());
            if (fpinfo.getReturnCode().equals("0000")) {
                try {
                    FPQZService.FPQZ(fpinfo, webservice_url_qz, platform_registrationcode, platform_authorizationcode, platform_erpcode, pdf_path);
                } catch (Exception e) {
                    fpInfoMapper.insertFPInfo(fpinfo);
                    fpInfoMapper.insertFpTxnInfo(fpinfo);
                    map.put("data", e.getMessage());
                    return map;
                }
            } else {
                //开票请求失败，直接返回
                map.put("data", fpinfo.getReturnCode() + fpinfo.getReturnMessage());
                return map;
            }
            //开票成功，签章成功
            fpinfo.setStatus("1");
            fpinfo.setFp_status("1");
            //插入发票记录
            fpInfoMapper.insertFPInfo(fpinfo);
            //插入发票交易表
            fpInfoMapper.insertFpTxnInfo(fpinfo);
            //更新充值记录表
            fpInfoMapper.updateCardsvInfo(fpinfo.getCardno(), fpinfo.getRef_key(), fpinfo.getFp_hm(), fpinfo.getFp_dm());
        }
        logger.info("开票成功，请留意您的电子邮箱或点击<a href=\""+fpinfo.getPdf_url()+"\">下载PDF</a>");
        map.put("data", "开票成功，请留意您的电子邮箱或点击<a href=\""+fpinfo.getPdf_url()+"\">下载PDF</a>");
        activeMQProducer.send(fpinfo.getFpqqlsh());
        return map;

    }
    /*
    @RequestMapping(value = "/TEST", method = RequestMethod.GET)
    public void invoice(HttpServletRequest request, HttpServletResponse response){
        activeMQProducer.send("hello world");
    }
    */


}
