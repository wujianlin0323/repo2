package com.jieyi.util;

import com.aisino.cxf.CXFClientUtil;
import com.aisino.cxf.IEIzzs_kpfwService;
import com.jieyi.controller.InvoiceController;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import com.jieyi.model.fpInfo;
import com.jieyi.model.globalInfo;

import java.text.DecimalFormat;

@Configuration
public class FPKPService {
    private static Logger logger = LoggerFactory.getLogger(InvoiceController.class);
    private final static DecimalFormat fmt = new DecimalFormat("##,###,###,###,##0.00");

    public static String convertFPKJ(fpInfo fpinfo) {

        fpinfo.setBmb_bbh("29.0");
        globalInfo globalinfo = new globalInfo();
        /*全局信息*/
        globalinfo.setAppId("ZZSDZFP");
        globalinfo.setInterfaceCode(CommonConstants.EI_FPKJ_U_EC_INTRA);/*接口编码*/
        globalinfo.setUserName(fpinfo.getXhf_nsrsbh());/*平台编码*/
        globalinfo.setDataExchangeId(fpinfo.getFpqqlsh());
        globalinfo.setZipCode("0");/*压缩标识*/
        globalinfo.setEncrypCode("0");/*加密标识*/
        globalinfo.setCodeType("0");/*加密方式*/

        return GetFPKJXml(globalinfo, fpinfo);
    }


    /**
     * 获取开票报文
     *
     * @return
     */

    private static String GetFPKJXml(globalInfo globalinfo, fpInfo fpinfo) {
        StringBuilder sbOut = new StringBuilder();
        sbOut.append("<REQUEST_FPKJ class=\"REQUEST_FPKJ\">");
        sbOut.append("<GHFMC>" + fpinfo.getGhfmc() + "</GHFMC>");                    //购货方名称
        if (fpinfo.getGhf_nsrsbh() == null) {
            sbOut.append("<GHF_NSRSBH></GHF_NSRSBH>");
        } else {
            sbOut.append("<GHF_NSRSBH>" + fpinfo.getGhf_nsrsbh() + "</GHF_NSRSBH>");    //购货方识别号  购货方名称，即发票抬头。
        }

        if (fpinfo.getGhf_yhzh() == null) {
            sbOut.append("<FKFKHYH_FKFYHZH></FKFKHYH_FKFYHZH>"); //付款方银行及账号
        } else {
            sbOut.append("<FKFKHYH_FKFYHZH>" + fpinfo.getGhf_yhzh() + "</FKFKHYH_FKFYHZH>"); //付款方银行及账号
        }

        if (fpinfo.getGhf_dzdh() == null) {
            sbOut.append("<FKFDZ_FKFDH></FKFDZ_FKFDH>");//付款方地址电话
        } else {
            sbOut.append("<FKFDZ_FKFDH>" + fpinfo.getGhf_dzdh() + "</FKFDZ_FKFDH>");//付款方地址电话
        }

        if (fpinfo.getXhf_yhzh() == null) {
            sbOut.append("<XHFKHYH_SKFYHZH></XHFKHYH_SKFYHZH>");//销货方银行及账号
        } else {
            sbOut.append("<XHFKHYH_SKFYHZH>" + fpinfo.getXhf_yhzh() + "</XHFKHYH_SKFYHZH>");//销货方银行及账号
        }
        if (fpinfo.getXhf_dzdh() == null) {
            sbOut.append("<XHFDZ_XHFDH></XHFDZ_XHFDH>");//销货方地址电话
        } else {
            sbOut.append("<XHFDZ_XHFDH>" + fpinfo.getXhf_dzdh() + "</XHFDZ_XHFDH>");//销货方地址电话
        }

        sbOut.append("<FPZL_DM>51</FPZL_DM>");
        if (fpinfo.getYfp_dm() == null) {
            sbOut.append("<YFP_DM/>");
        } else {
            sbOut.append("<YFP_DM>" + fpinfo.getYfp_dm() + "</YFP_DM>");
        }
        if (fpinfo.getYfp_hm() == null) {
            sbOut.append("<YFP_HM/>");
        } else {
            sbOut.append("<YFP_HM>" + fpinfo.getYfp_hm() + "</YFP_HM>");
        }
        sbOut.append("<BZ/>");
        sbOut.append("<KPY>" + fpinfo.getKpy() + "</KPY>");
        sbOut.append("<FHR/>");
        sbOut.append("<SKY/>");
        sbOut.append("<XHQD/>");
        sbOut.append("<FPQQLSH>" + fpinfo.getFpqqlsh() + "</FPQQLSH>");                //发票请求唯一流水号 每张发票的发票请求唯一流水号无重复，由企业定义。限制固定 20 位。
        sbOut.append("<KPLX>" + fpinfo.getKplx() + "</KPLX>");
        sbOut.append("<JSHJ>" + fpinfo.getKphjje() + "</JSHJ>");        //价税合计金额  小数点后 2 位，以元为单位精确到分
        sbOut.append("<HJJE>" + fpinfo.getHjbhsje() + "</HJJE>");        //合计不含税金额。所有商品行不含税金额之和。小数点后 2 位，以	元为单位精确到分（单行商品金额之和） 。平台处理价税分离，此值传 0小数点后 2 位，以元为单位精确到分（单行商品金额之和） 。平台处理价税分离，此值传 0
        sbOut.append("<HJSE>" + fpinfo.getHjse() + "</HJSE>");            //合计税额。 所有商品行税额之和。小数点后 2 位，以元为单位精确到分(单行商品税额之和)，平台处理价税	分离，此值传 0
        sbOut.append("<BMB_BBH>" + fpinfo.getBmb_bbh() + "</BMB_BBH>");

        sbOut.append("<FP_KJMXS class=\"FP_KJMX;\" size=\"1\">");
        sbOut.append("<FP_KJMX>");
        sbOut.append("<SPMC>" + fpinfo.getSpmc() + "</SPMC>");                //商品名称
        sbOut.append("<SM>" + fpinfo.getSm() + "</SM>");                //税目
        sbOut.append("<SL>" + fpinfo.getSl() + "</SL>");            //税率  如果税率为 0， 表示免税
        sbOut.append("<GGXH>" + fpinfo.getGgxh() + "</GGXH>");//规格型号
        sbOut.append("<JLDW>" + fpinfo.getJldw() + "</JLDW>");            //计量单位
        sbOut.append("<SPSL>" + fpinfo.getSpsl() + "</SPSL>");            //商品数量
        sbOut.append("<SPDJ>" + fpinfo.getSpdj() + "</SPDJ>");            //商品单价
        sbOut.append("<SPJE>" + fpinfo.getSpje() + "</SPJE>");            //商品金额
        sbOut.append("<FPHXZ>" + fpinfo.getFphxz() + "</FPHXZ>");        //发票行性质
        sbOut.append("<HSJBZ>" + fpinfo.getHsbz() + "</HSJBZ>");        //含税价标志  表示项目单价和项目金额是否含税。0表示都不含税， 1 表示都含税。
        sbOut.append("<SE>" + fpinfo.getSe() + "</SE>");            //税额  小数点后 2 位，以元为单位精确到分
        sbOut.append("<SPBM>" + fpinfo.getSpbm() + "</SPBM>");        //商品编码
        sbOut.append("<ZXBM/>");        //自行编码
        sbOut.append("<YHZCBS>" + fpinfo.getYhzcbs() + "</YHZCBS>");        //优惠政策标识
        sbOut.append("<LSLBS/>");        //零税率标识
        sbOut.append("<ZZSTSGL/>");        //增值税特殊管理
        sbOut.append("</FP_KJMX>");
        sbOut.append("</FP_KJMXS>");
        sbOut.append("</REQUEST_FPKJ>");

        logger.info("content=" + sbOut.toString());
        String content = null;
        try {
            //content= new String(Base64.encodeBase64(PKCS7.CAEncryptByteClient(sbOut.toString(), globalinfo.getTaxpayerId())), "utf-8");
            content = new String(Base64.encodeBase64(sbOut.toString().getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        globalinfo.setContent(content);
        return GetFPKJTotalXml(globalinfo);
    }

    /**
     * 获取平台外层报文并进行加密操作
     *
     * @param interfaceCode
     * @param contentXml
     * @param customer
     * @return
     */

    private static String GetFPKJTotalXml(globalInfo globalinfo) {
        StringBuilder sbOut = new StringBuilder();
        sbOut.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
        sbOut.append("<interface xmlns=\"\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.chinatax.gov.cn/tirip/dataspec/interfaces.xsd\" version=\"DZFP1.0\">");
        sbOut.append("<globalInfo>");
        sbOut.append("<appId>" + globalinfo.getAppId() + "</appId>");
        sbOut.append("<interfaceCode>" + globalinfo.getInterfaceCode() + "</interfaceCode>");
        sbOut.append("<userName>" + globalinfo.getUserName() + "</userName>");
        sbOut.append("<passWord/>");
        sbOut.append("<requestCode/>");
        sbOut.append("<requestTime/>");
        sbOut.append("<responseCode/>");
        sbOut.append("<dataExchangeId>" + globalinfo.getDataExchangeId() + "</dataExchangeId>");
        sbOut.append("<fjh/>");
        sbOut.append("<jqbh/>");
        sbOut.append("</globalInfo>");
        sbOut.append("<returnStateInfo>");
        sbOut.append("<returnCode/>");
        sbOut.append("<returnMessage/>");
        sbOut.append("</returnStateInfo>");
        sbOut.append("<Data>");
        sbOut.append("<dataDescription>");
        sbOut.append("<zipCode>" + globalinfo.getZipCode() + "</zipCode>");
        sbOut.append("<encryptCode>" + globalinfo.getEncrypCode() + "</encryptCode>");
        sbOut.append("<codeType>" + globalinfo.getCodeType() + "</codeType>");
        sbOut.append("</dataDescription>");
        sbOut.append("<content>" + globalinfo.getContent() + "</content>");
        sbOut.append("</Data>");
        sbOut.append("</interface>");
        logger.info("goble=" + sbOut.toString());
        return sbOut.toString();
    }


    /**
     * 发票开具方法（蓝字）
     */
    public static void FPKJ(fpInfo fpinfo, String webservice_url) throws Exception {

        String xmlStr = convertFPKJ(fpinfo);
        logger.info("xmlStr=" + xmlStr);
        try {
            IEIzzs_kpfwService IEIzzs_kpfwService = (IEIzzs_kpfwService) CXFClientUtil.getJaxWsProxy(IEIzzs_kpfwService.class, webservice_url);
            String response = IEIzzs_kpfwService.eiInterface(xmlStr);
            Mapx map = Mapx.fromXml(response);
            // System.out.println(map.getDocument().asXML());
            fpinfo.setReturnCode(map.get("returnCode").toString());
            fpinfo.setReturnMessage(Base64Helper.decode(map.getString("returnMessage")));
            System.out.println(map.get("returnCode").toString());
            System.out.println(Base64Helper.decode(map.getString("returnMessage")));


            if (map.get("returnCode").toString().equals("0000")) {
                fpinfo.setJqbh(map.getString("jqbh"));
                String retContent = Base64Helper.decode(map.getString("content"));
                Mapx contentMap = Mapx.fromXml(retContent);
                fpinfo.setKprq(contentMap.getString("KPRQ"));
                fpinfo.setSsyf(contentMap.getString("SSYF"));
                fpinfo.setFp_dm(contentMap.getString("FP_DM"));
                fpinfo.setFp_hm(contentMap.getString("FP_HM"));
                fpinfo.setFwmw(contentMap.getString("FWMW"));
                fpinfo.setRetcode(contentMap.getString("RETCODE"));
                fpinfo.setJym(contentMap.getString("JYM"));
                fpinfo.setSzqm(contentMap.getString("SZQM"));
                fpinfo.setEwm(contentMap.getString("EWM"));
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }


}
