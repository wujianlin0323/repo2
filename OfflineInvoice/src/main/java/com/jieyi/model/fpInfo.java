package com.jieyi.model;

public class fpInfo {
    private String cardno;
    private String txnamt;
    private String ref_key;
    private String inntype;
    private String txndate;
    private String status;/*开票状态，0:未开,1:已开*/

    /*以下为开票信息*/
    private String ghfmc;      /*购货方名称*/
    private String ghf_nsrsbh;  /*购货方识别号*/
    private String ghfqylx;     /*购货方企业类型*/
    private String ghf_yhzh;    /*购货方银行账号*/
    private String ghf_dz;      /*购货方地址*/
    private String ghf_sh;      /*购货方省份*/
    private String ghf_gddh;    /*购货方固定电话*/
    private String ghf_sj;      /*购货方手机*/
    private String ghf_email;    /*购货方邮箱*/
    private String ghf_dzdh;     /*购货方地址电话*/

    private String xhf_dzdh;      /*销货方地址电话*/
    private String xhf_nsrsbh;  /*销货方识别号*/
    private String xhfmc;        /*销货方名称*/
    private String xhf_dz;        /*销货方地址*/
    private String xhf_dh;         /*销货方电话*/
    private String xhf_yhzh;      /*销货方银行账号*/
    private String yfp_dm;      /*原发票代码*/
    private String yfp_hm;       /*原发票号码*/
    private String kpy;         /*开票员*/
    private String sky;       /*收款员*/
    private String fhr;       /*复核人*/
    private String qd_bz;       /*清单标志*/
    private String fpqqlsh;  /*发票请求唯一流水号*/
    private String kplx;        /*开票类型0-蓝票，1-红票*/
    private String kphjje;       /*价税合计金额*/
    private String hjbhsje;      /*不含税金额*/
    private String hjse;         /*合计税额*/
    private String spmc;
    private String sm;
    private String sl;        /*税率*/
    private String ggxh;
    private String jldw;
    private String spsl;
    private String spdj;     /*商品单价*/
    private String spje;     /*商品金额*/
    private String fphxz;    /*发票行性质*/
    private String hsbz;     /*含税标识*/
    private String se;         /*税额*/
    private String spbm;     /*商品编码*/
    private String zxbm;
    private String yhzcbs;   /*优惠政策标识*/
    private String lslbs;    /*零税率标识*/
    private String zzstsgl;  /*增值税特殊管理*/
    private String tsfs;      /*推送方式*/
    private String fplx;   /*发票类型1-增普，2-增专，3-普通发票*/
    private String sjly;   /*数据来源*/


    /*****************/
    private String dkbz;     /*代开标志*/
    private String bmb_bbh;    /*编码表版本号*/
    private String txcsbz;        /*特殊红冲标志*/
    private String qdxmmc;      /*清单发票项目名称，清单标志为1时，必填*/
    /*end*/

    /*返回结果*/
    private String jqbh;
    private String returnCode;   /*返回代码*/
    private String returnMessage; /*返回描述*/
    private String kprq;    /*开票日期*/
    private String ssyf;   /*所属月份*/
    private String fp_dm;   /*发票代码*/
    private String fp_hm;  /*发票号码*/
    private String retcode;  /*底层返回代码*/
    private String fwmw;   /*防伪密文*/
    private String jym;   /*校验码*/
    private String szqm;   /*签名值*/
    private String ewm;   /*二维码*/

    private String returnCodeQZ;
    private String returnMessageQZ;

    private String pdf_file; /*pdf文件*/
    private String pdf_url;   /*pdf下载路径*/

    private String fp_status;

    private String txn_src;

    private String fprq;//发票日期

    private String cancelflag;/*原交易撤销标识*/

    public String getSm() {
        return sm;
    }

    public void setSm(String sm) {
        this.sm = sm;
    }

    public String getCardno() {
        return cardno;
    }

    public void setCardno(String cardno) {
        this.cardno = cardno;
    }

    public String getTxnamt() {
        return txnamt;
    }

    public void setTxnamt(String txnamt) {
        this.txnamt = txnamt;
    }

    public String getRef_key() {
        return ref_key;
    }

    public void setRef_key(String ref_key) {
        this.ref_key = ref_key;
    }

    public String getInntype() {
        return inntype;
    }

    public void setInntype(String inntype) {
        this.inntype = inntype;
    }

    public String getFpqqlsh() {
        return fpqqlsh;
    }

    public void setFpqqlsh(String fpqqlsh) {
        this.fpqqlsh = fpqqlsh;
    }

    public String getDkbz() {
        return dkbz;
    }

    public void setDkbz(String dkbz) {
        this.dkbz = dkbz;
    }


    public String getBmb_bbh() {
        return bmb_bbh;
    }

    public void setBmb_bbh(String bmb_bbh) {
        this.bmb_bbh = bmb_bbh;
    }

    public String getXhf_nsrsbh() {
        return xhf_nsrsbh;
    }

    public void setXhf_nsrsbh(String xhf_nsrsbh) {
        this.xhf_nsrsbh = xhf_nsrsbh;
    }

    public String getXhfmc() {
        return xhfmc;
    }

    public void setXhfmc(String xhfmc) {
        this.xhfmc = xhfmc;
    }

    public String getXhf_dz() {
        return xhf_dz;
    }

    public void setXhf_dz(String xhf_dz) {
        this.xhf_dz = xhf_dz;
    }

    public String getXhf_dh() {
        return xhf_dh;
    }

    public void setXhf_dh(String xhf_dh) {
        this.xhf_dh = xhf_dh;
    }

    public String getXhf_yhzh() {
        return xhf_yhzh;
    }

    public void setXhf_yhzh(String xhf_yhzh) {
        this.xhf_yhzh = xhf_yhzh;
    }

    public String getGhfmc() {
        return ghfmc;
    }

    public void setGhfmc(String ghfmc) {
        this.ghfmc = ghfmc;
    }

    public String getGhf_nsrsbh() {
        return ghf_nsrsbh;
    }

    public void setGhf_nsrsbh(String ghf_nsrsbh) {
        this.ghf_nsrsbh = ghf_nsrsbh;
    }

    public String getGhf_dz() {
        return ghf_dz;
    }

    public void setGhf_dz(String ghf_dz) {
        this.ghf_dz = ghf_dz;
    }

    public String getGhf_sh() {
        return ghf_sh;
    }

    public void setGhf_sh(String ghf_sh) {
        this.ghf_sh = ghf_sh;
    }

    public String getGhf_gddh() {
        return ghf_gddh;
    }

    public void setGhf_gddh(String ghf_gddh) {
        this.ghf_gddh = ghf_gddh;
    }

    public String getGhf_sj() {
        return ghf_sj;
    }

    public void setGhf_sj(String ghf_sj) {
        this.ghf_sj = ghf_sj;
    }

    public String getGhf_email() {
        return ghf_email;
    }

    public void setGhf_email(String ghf_email) {
        this.ghf_email = ghf_email;
    }

    public String getGhfqylx() {
        return ghfqylx;
    }

    public void setGhfqylx(String ghfqylx) {
        this.ghfqylx = ghfqylx;
    }

    public String getGhf_yhzh() {
        return ghf_yhzh;
    }

    public void setGhf_yhzh(String ghf_yhzh) {
        this.ghf_yhzh = ghf_yhzh;
    }

    public String getKpy() {
        return kpy;
    }

    public void setKpy(String kpy) {
        this.kpy = kpy;
    }

    public String getSky() {
        return sky;
    }

    public void setSky(String sky) {
        this.sky = sky;
    }

    public String getFhr() {
        return fhr;
    }

    public void setFhr(String fhr) {
        this.fhr = fhr;
    }

    public String getKprq() {
        return kprq;
    }

    public void setKprq(String kprq) {
        this.kprq = kprq;
    }

    public String getKplx() {
        return kplx;
    }

    public void setKplx(String kplx) {
        this.kplx = kplx;
    }

    public String getYfp_dm() {
        return yfp_dm;
    }

    public void setYfp_dm(String yfp_dm) {
        this.yfp_dm = yfp_dm;
    }

    public String getYfp_hm() {
        return yfp_hm;
    }

    public void setYfp_hm(String yfp_hm) {
        this.yfp_hm = yfp_hm;
    }


    public String getTxcsbz() {
        return txcsbz;
    }

    public void setTxcsbz(String txcsbz) {
        this.txcsbz = txcsbz;
    }

    public String getQd_bz() {
        return qd_bz;
    }

    public void setQd_bz(String qd_bz) {
        this.qd_bz = qd_bz;
    }

    public String getQdxmmc() {
        return qdxmmc;
    }

    public void setQdxmmc(String qdxmmc) {
        this.qdxmmc = qdxmmc;
    }

    public String getKphjje() {
        return kphjje;
    }

    public void setKphjje(String kphjje) {
        this.kphjje = kphjje;
    }

    public String getHjbhsje() {
        return hjbhsje;
    }

    public void setHjbhsje(String hjbhsje) {
        this.hjbhsje = hjbhsje;
    }

    public String getHjse() {
        return hjse;
    }

    public void setHjse(String hjse) {
        this.hjse = hjse;
    }


    public String getGgxh() {
        return ggxh;
    }

    public void setGgxh(String ggxh) {
        this.ggxh = ggxh;
    }


    public String getHsbz() {
        return hsbz;
    }

    public void setHsbz(String hsbz) {
        this.hsbz = hsbz;
    }

    public String getFphxz() {
        return fphxz;
    }

    public void setFphxz(String fphxz) {
        this.fphxz = fphxz;
    }


    public String getSpbm() {
        return spbm;
    }

    public void setSpbm(String spbm) {
        this.spbm = spbm;
    }

    public String getYhzcbs() {
        return yhzcbs;
    }

    public void setYhzcbs(String yhzcbs) {
        this.yhzcbs = yhzcbs;
    }

    public String getLslbs() {
        return lslbs;
    }

    public void setLslbs(String lslbs) {
        this.lslbs = lslbs;
    }

    public String getZzstsgl() {
        return zzstsgl;
    }

    public void setZzstsgl(String zzstsgl) {
        this.zzstsgl = zzstsgl;
    }


    public String getSl() {
        return sl;
    }

    public void setSl(String sl) {
        this.sl = sl;
    }

    public String getSe() {
        return se;
    }

    public void setSe(String se) {
        this.se = se;
    }


    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(String returnMessage) {
        this.returnMessage = returnMessage;
    }

    public String getSsyf() {
        return ssyf;
    }

    public void setSsyf(String ssyf) {
        this.ssyf = ssyf;
    }

    public String getFp_dm() {
        return fp_dm;
    }

    public void setFp_dm(String fp_dm) {
        this.fp_dm = fp_dm;
    }

    public String getFp_hm() {
        return fp_hm;
    }

    public void setFp_hm(String fp_hm) {
        this.fp_hm = fp_hm;
    }

    public String getRetcode() {
        return retcode;
    }

    public void setRetcode(String retcode) {
        this.retcode = retcode;
    }

    public String getFwmw() {
        return fwmw;
    }

    public void setFwmw(String fwmw) {
        this.fwmw = fwmw;
    }

    public String getJym() {
        return jym;
    }

    public void setJym(String jym) {
        this.jym = jym;
    }

    public String getSzqm() {
        return szqm;
    }

    public void setSzqm(String szqm) {
        this.szqm = szqm;
    }

    public String getEwm() {
        return ewm;
    }

    public void setEwm(String ewm) {
        this.ewm = ewm;
    }


    public String getSpdj() {
        return spdj;
    }

    public void setSpdj(String spdj) {
        this.spdj = spdj;
    }

    public String getSpje() {
        return spje;
    }

    public void setSpje(String spje) {
        this.spje = spje;
    }

    public String getJldw() {
        return jldw;
    }

    public void setJldw(String jldw) {
        this.jldw = jldw;
    }

    public String getSpsl() {
        return spsl;
    }

    public void setSpsl(String spsl) {
        this.spsl = spsl;
    }

    public String getSpmc() {
        return spmc;
    }

    public void setSpmc(String spmc) {
        this.spmc = spmc;
    }

    public String getZxbm() {
        return zxbm;
    }

    public void setZxbm(String zxbm) {
        this.zxbm = zxbm;
    }

    public String getTsfs() {
        return tsfs;
    }

    public void setTsfs(String tsfs) {
        this.tsfs = tsfs;
    }

    public String getPdf_file() {
        return pdf_file;
    }

    public void setPdf_file(String pdf_file) {
        this.pdf_file = pdf_file;
    }

    public String getPdf_url() {
        return pdf_url;
    }

    public void setPdf_url(String pdf_url) {
        this.pdf_url = pdf_url;
    }

    public String getJqbh() {
        return jqbh;
    }

    public void setJqbh(String jqbh) {
        this.jqbh = jqbh;
    }

    public String getFplx() {
        return fplx;
    }

    public void setFplx(String fplx) {
        this.fplx = fplx;
    }

    public String getSjly() {
        return sjly;
    }

    public void setSjly(String sjly) {
        this.sjly = sjly;
    }

    public String getGhf_dzdh() {
        return ghf_dzdh;
    }

    public void setGhf_dzdh(String ghf_dzdh) {
        this.ghf_dzdh = ghf_dzdh;
    }

    public String getXhf_dzdh() {
        return xhf_dzdh;
    }

    public void setXhf_dzdh(String xhf_dzdh) {
        this.xhf_dzdh = xhf_dzdh;
    }

    public String getReturnCodeQZ() {
        return returnCodeQZ;
    }

    public void setReturnCodeQZ(String returnCodeQZ) {
        this.returnCodeQZ = returnCodeQZ;
    }

    public String getReturnMessageQZ() {
        return returnMessageQZ;
    }

    public void setReturnMessageQZ(String returnMessageQZ) {
        this.returnMessageQZ = returnMessageQZ;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTxndate() {
        return txndate;
    }

    public void setTxndate(String txndate) {
        this.txndate = txndate;
    }

    public String getFp_status() {
        return fp_status;
    }

    public void setFp_status(String fp_status) {
        this.fp_status = fp_status;
    }

    public String getTxn_src() {
        return txn_src;
    }

    public void setTxn_src(String txn_src) {
        this.txn_src = txn_src;
    }

    public String getFprq() {
        return fprq;
    }

    public void setFprq(String fprq) {
        this.fprq = fprq;
    }

    public String getCancelflag() {
        return cancelflag;
    }

    public void setCancelflag(String cancelflag) {
        this.cancelflag = cancelflag;
    }
}
