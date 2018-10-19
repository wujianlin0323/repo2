package com.jieyi.util;

import com.aisino.cxf.CXFClientUtil;
import com.aisino.cxf.IEIfpqzService;
import com.jieyi.controller.InvoiceController;
import com.jieyi.model.fpInfo;
import com.jieyi.model.globalInfo;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.util.HtmlUtils;

import java.text.DecimalFormat;
import java.util.Date;
import java.io.FileOutputStream;
import java.io.File;

import com.aisino.PKCS7;

@Configuration
public class FPQZService {
    private static Logger logger = LoggerFactory.getLogger(InvoiceController.class);
    private final static DecimalFormat fmt = new DecimalFormat("##,###,###,###,##0.00");


    public static String convertFPQZ(fpInfo fpinfo, String platform_registrationcode, String platform_erpcode, String platform_authorizationcode) throws Exception {
        fpinfo.setBmb_bbh("1.0");
        globalInfo globalinfo = new globalInfo();

        // fpinfo.setReturnCode("");  /*置空返回码*/
        /*全局信息*/
        globalinfo.setTerminalCode("0");
        globalinfo.setAppId("A16D8DBD18EDDD80");/*应用标识，DZFP：普通发票，ZZS_PT_DZFP：增值税普通电子发票*/
        globalinfo.setInterfaceCode(CommonConstants.ECXML_FPQZ_BC_E_INV);/*接口编码*/

        globalinfo.setUserName(fpinfo.getXhf_nsrsbh());/*平台编码*/
        String random = RandomUtil.produceByDigit(10);
        globalinfo.setPassWord(random + Base64.encodeBase64String(MD5.digestByte(random + platform_registrationcode)));/*密码*/
        globalinfo.setRequestCode(platform_erpcode);/*用平台编码*/
        globalinfo.setReqestTime(DateFormatUtils.format(new Date(), "yyyyMMddHHmmss"));/**/
        globalinfo.setTaxpayerId(fpinfo.getXhf_nsrsbh());/*纳税人识别号*/
        globalinfo.setAnthorizationCode(platform_authorizationcode);/*纳税人授权码*/
        globalinfo.setReponseCode("135");

        globalinfo.setDataExchangeId("RCYKT" + DateFormatUtils.format(new Date(), "yyyyMMdd") + RandomUtil.produceNumberLetterByDigit(9));
        globalinfo.setZipCode("0");/*压缩标识*/
        globalinfo.setEncrypCode("2");/*加密标识*/
        globalinfo.setCodeType("CA");/*加密方式*/

        return GetFPQZXml(globalinfo, fpinfo);
    }

    /*
     * 获取发票签章内层报文
     * */
    private static String GetFPQZXml(globalInfo globalinfo, fpInfo fpinfo) throws Exception {
        StringBuilder sbOut = new StringBuilder();
        String fpqz_info = null;
        String info = "SJLY\u0002" + fpinfo.getSjly() + "\u0003FPLX\u0002" + fpinfo.getFplx();
        //String info1="SJLY "+fpinfo.getSjly()+" FPLX "+fpinfo.getFplx();
        try {
            //fpqz_info=new String(Base64.encodeBase64(("SJLY(char)2"+fpinfo.getSjly()+"(char)3FPLX(char)2"+fpinfo.getFplx()).getBytes()),"utf-8");
            fpqz_info = new String(Base64.encodeBase64(info.getBytes()), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        sbOut.append("<REQUEST_FPQZ class=\"REQUEST_FPQZ\">");
        sbOut.append("<FPQZ_INFO>" + fpqz_info + "</FPQZ_INFO>");
        sbOut.append("<FPQZ_FPT class=\"FPQZ_FPT\">");
        sbOut.append("<FPQQLSH>" + fpinfo.getFpqqlsh() + "</FPQQLSH>");                //发票请求唯一流水号 每张发票的发票请求唯一流水号无重复，由企业定义。限制固定 20 位。
        sbOut.append("<KPLX>" + fpinfo.getKplx() + "</KPLX>");
        sbOut.append("<DKBZ>0</DKBZ>");
        sbOut.append("<BMB_BBH>" + fpinfo.getBmb_bbh() + "</BMB_BBH>");
        sbOut.append("<QD_BZ>" + fpinfo.getQd_bz() + "</QD_BZ>");
        sbOut.append("<QDXMMC></QDXMMC>");
        sbOut.append("<XSF_NSRSBH>" + fpinfo.getXhf_nsrsbh() + "</XSF_NSRSBH>");        //销货方识别号  必填，如果是企业自营开具发票，填写第 3 项中的开票方识别号，如果是商家驻店开具发票，填写商家的纳税人识别号
        sbOut.append("<XSF_MC>" + fpinfo.getXhfmc() + "</XSF_MC>");    //销货方名称

        if (fpinfo.getXhf_dzdh() == null) {
            sbOut.append("<XSF_DZDH></XSF_DZDH>");//销货方地址电话
        } else {
            sbOut.append("<XSF_DZDH>" + fpinfo.getXhf_dzdh() + "</XSF_DZDH>");//销货方地址电话
        }
        if (fpinfo.getXhf_yhzh() == null) {
            sbOut.append("<XSF_YHZH></XSF_YHZH>");//销货方银行及账号
        } else {
            sbOut.append("<XSF_YHZH>" + fpinfo.getXhf_yhzh() + "</XSF_YHZH>");//销货方银行及账号
        }

        if (fpinfo.getGhf_nsrsbh() == null) {
            sbOut.append("<GMF_NSRSBH></GMF_NSRSBH>");
        } else {
            sbOut.append("<GMF_NSRSBH>" + fpinfo.getGhf_nsrsbh() + "</GMF_NSRSBH>");    //购货方识别号  购货方名称，即发票抬头。
        }

        sbOut.append("<GMF_MC>" + fpinfo.getGhfmc() + "</GMF_MC>");                    //购货方名称
        if (fpinfo.getGhf_dzdh() == null) {
            sbOut.append("<GMF_DZDH></GMF_DZDH>");//付款方地址电话
        } else {
            sbOut.append("<GMF_DZDH>" + fpinfo.getGhf_dzdh() + "</GMF_DZDH>");//购货方地址电话
        }
        if (fpinfo.getGhf_yhzh() == null) {
            sbOut.append("<GMF_YHZH></GMF_YHZH>"); //付款方银行及账号
        } else {
            sbOut.append("<GMF_YHZH>" + fpinfo.getGhf_yhzh() + "</GMF_YHZH>"); //付款方银行及账号
        }


        sbOut.append("<KPR>" + fpinfo.getKpy() + "</KPR>");
        sbOut.append("<FHR></FHR>");
        sbOut.append("<SKR></SKR>");
        if (fpinfo.getYfp_dm() == null) {
            sbOut.append("<YFP_DM></YFP_DM>");
        } else {
            sbOut.append("<YFP_DM>" + fpinfo.getYfp_dm() + "</YFP_DM>");
        }
        if (fpinfo.getYfp_hm() == null) {
            sbOut.append("<YFP_HM></YFP_HM>");
        } else {
            sbOut.append("<YFP_HM>" + fpinfo.getYfp_hm() + "</YFP_HM>");
        }
        sbOut.append("<JSHJ>" + fpinfo.getKphjje() + "</JSHJ>");        //价税合计金额
        sbOut.append("<HJJE>" + fpinfo.getHjbhsje() + "</HJJE>");        //合计不含税金额。
        sbOut.append("<HJSE>" + fpinfo.getHjse() + "</HJSE>");            //合计税额。 所有商品行税额之和。
        sbOut.append("<BZ></BZ>");
        sbOut.append("<FPZT>0</FPZT>");
        sbOut.append("<JQBH>" + fpinfo.getJqbh() + "</JQBH>");
        sbOut.append("<FP_DM>" + fpinfo.getFp_dm() + "</FP_DM>");
        sbOut.append("<FP_HM>" + fpinfo.getFp_hm() + "</FP_HM>");
        sbOut.append("<KPRQ>" + fpinfo.getKprq() + "</KPRQ>");
        sbOut.append("<FP_MW>" + HtmlUtils.htmlEscape(fpinfo.getFwmw()) + "</FP_MW>");

        sbOut.append("<JYM>" + fpinfo.getJym() + "</JYM>");
        sbOut.append("<EWM>" + fpinfo.getEwm() + "</EWM>");
        sbOut.append("<PDF_XZFS>3</PDF_XZFS>");

        sbOut.append("</FPQZ_FPT>");
        sbOut.append("<FPQZ_XMXXS class=\"FPQZ_XMXX;\" size=\"1\">");
        sbOut.append("<FPQZ_XMXX>");
        sbOut.append("<XMMC>" + fpinfo.getSpmc() + "</XMMC>");        //项目名称
        sbOut.append("<DW>" + fpinfo.getJldw() + "</DW>");            //计量单位
        sbOut.append("<GGXH>" + fpinfo.getGgxh() + "</GGXH>");      //规格型号
        sbOut.append("<XMSL>" + fpinfo.getSpsl() + "</XMSL>");        //项目数量
        sbOut.append("<XMDJ>" + fpinfo.getSpdj() + "</XMDJ>");        //项目单价
        sbOut.append("<XMJE>" + fpinfo.getSpje() + "</XMJE>");        //项目金额
        sbOut.append("<SL>" + fpinfo.getSl() + "</SL>");            //税率  如果税率为 0， 表示免税
        sbOut.append("<SE>" + fpinfo.getSe() + "</SE>");            //税额  小数点后 2 位，以元为单位精确到分
        sbOut.append("<HSJBZ>" + fpinfo.getHsbz() + "</HSJBZ>");        //含税价标志  0表示都不含税， 1 表示都含税。
        sbOut.append("<FPHXZ>" + fpinfo.getFphxz() + "</FPHXZ>");        //发票行性质
        sbOut.append("<SPBM>" + fpinfo.getSpbm() + "</SPBM>");        //商品编码
        sbOut.append("<ZXBM/>");        //自行编码
        sbOut.append("<YHZCBS>" + fpinfo.getYhzcbs() + "</YHZCBS>");        //优惠政策标识
        sbOut.append("<LSLBS/>");        //零税率标识
        sbOut.append("<ZZSTSGL/>");        //增值税特殊管理
        sbOut.append("<KCE/>");        //扣除额
        sbOut.append("</FPQZ_XMXX>");
        sbOut.append("</FPQZ_XMXXS>");
        sbOut.append("</REQUEST_FPQZ>");

        logger.info("content=" + sbOut.toString());
        String content = null;
        try {
            content = new String(Base64.encodeBase64(PKCS7.CAEncryptByteClient(sbOut.toString())), "utf-8");
            //content= new String(Base64.encodeBase64(sbOut.toString().getBytes()), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        globalinfo.setContent(content);
        return GetFPQZTotalXml(globalinfo);
    }


    /**
     * 获取平台外层报文并进行加密操作
     *
     * @param interfaceCode
     * @param contentXml
     * @param customer
     * @return
     */

    private static String GetFPQZTotalXml(globalInfo globalinfo) {
        StringBuilder sbOut = new StringBuilder();
        sbOut.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
        sbOut.append("<interface  xmlns=\"\"  xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.chinatax.gov.cn/tirip/dataspec/interfaces.xsd\" version=\"DZFP1.0\">");
        sbOut.append("<globalInfo>");
        sbOut.append("<terminalCode>" + globalinfo.getTerminalCode() + "</terminalCode>");
        sbOut.append("<appId>" + globalinfo.getAppId() + "</appId>");
        sbOut.append("<version>2.0</version>");
        sbOut.append("<interfaceCode>" + globalinfo.getInterfaceCode() + "</interfaceCode>");
        sbOut.append("<userName>" + globalinfo.getUserName() + "</userName>");
        sbOut.append("<passWord>" + globalinfo.getPassWord() + "</passWord>");
        sbOut.append("<requestCode>" + globalinfo.getRequestCode() + "</requestCode>");
        sbOut.append("<requestTime>" + globalinfo.getReqestTime() + "</requestTime>");
        sbOut.append("<taxpayerId>" + globalinfo.getTaxpayerId() + "</taxpayerId>");
        sbOut.append("<authorizationCode>" + globalinfo.getAnthorizationCode() + "</authorizationCode>");
        sbOut.append("<responseCode/>");
        sbOut.append("<dataExchangeId>" + globalinfo.getDataExchangeId() + "</dataExchangeId>");
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
     * 发票签章
     */
    public static void FPQZ(fpInfo fpinfo, String webservice_url, String platform_registrationcode,
                            String platform_authorizationcode, String platform_erpcode, String pdf_path) throws Exception {
        try {
            String xmlStr = convertFPQZ(fpinfo, platform_registrationcode, platform_erpcode, platform_authorizationcode);
            logger.info("xmlStr=" + xmlStr);
            IEIfpqzService EIfpqzService = (IEIfpqzService) CXFClientUtil.getJaxWsProxy(IEIfpqzService.class, webservice_url);
            String response = EIfpqzService.eiInterface(xmlStr);
            Mapx map = Mapx.fromXml(response);
            System.out.println(map.getDocument().asXML());
            fpinfo.setReturnCodeQZ(map.get("returnCode").toString());
            fpinfo.setReturnMessageQZ(Base64Helper.decode(map.getString("returnMessage")));
            System.out.println(map.get("returnCode").toString());
            System.out.println(Base64Helper.decode(map.getString("returnMessage")));


            if (map.get("returnCode").toString().equals("0000")) {
                byte[] uzipstr = GZipUtils.decompress(Base64Helper.decode(map.getString("content").getBytes("utf-8")));
                String response_content = PKCS7.CADecryptByteClient(uzipstr);
                //logger.info("response_content="+response_content);
                Mapx contentMap = Mapx.fromXml(response_content);
                fpinfo.setPdf_url(contentMap.getString("PDF_URL"));

                /*下载PDF文件到本地*/
                FileOutputStream out = new FileOutputStream(new File(pdf_path + File.separator + fpinfo.getFpqqlsh() + ".pdf"));
                out.write(Base64Helper.decode(contentMap.get("PDF_FILE").toString().getBytes()));
                fpinfo.setPdf_file(pdf_path + File.separator + fpinfo.getFpqqlsh() + ".pdf");
                out.flush();
                out.close();
            }


        } catch (Exception e) {
            e.printStackTrace();
            throw e;

        }


    }


}
