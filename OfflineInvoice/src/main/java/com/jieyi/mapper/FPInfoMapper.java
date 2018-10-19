package com.jieyi.mapper;

import org.apache.ibatis.annotations.*;
import com.jieyi.model.fpInfo;

/**
 * Created by yuyong on 2018/6/3.
 */
@Mapper
public interface FPInfoMapper {

    //@Select("select count(1) from  t_dtl_fptxn t where    t.cardno = #{cardno,jdbcType=VARCHAR}  and t.censeq = #{ref_key,jdbcType=VARCHAR} and t.fp_status ='1'")
    //int selectByTxnInfo(@Param("cardno") String cardno, @Param("ref_key") String ref_key);

    //@Select("select count(1) from  t_dtl_cardsv t where t.cardno = #{cardno,jdbcType=VARCHAR}  and t.ref_key = #{ref_key,jdbcType=VARCHAR} ")
    //int selectFromCardDtl(@Param("cardno") String cardno, @Param("ref_key") String ref_key);


    @Insert("insert into T_DTL_FPINFO(fprq,fpqqlsh,ghfmc,ghf_nsrsbh,ghfqylx,ghf_yhzh,ghf_dz,ghf_sh,ghf_gddh,ghf_sj,ghf_email,ghf_dzdh,xhf_dzdh,xhf_nsrsbh,xhfmc,xhf_dz,xhf_dh,xhf_yhzh,yfp_dm,yfp_hm,kpy,sky,fhr,qd_bz,kplx,kphjje,hjbhsje,hjse,spmc,sm,sl,ggxh,jldw,spsl,spdj,spje,fphxz,hsbz,se,spbm,zxbm,yhzcbs,lslbs,zzstsgl,tsfs,fplx,sjly,dkbz,bmb_bbh,txcsbz,qdxmmc,jqbh,returnCode,returnMessage,kprq,ssyf,fp_dm,fp_hm,retcode,fwmw,jym,szqm,ewm,returnCodeQZ,returnMessageQZ,pdf_file,pdf_url,status,updatetime)values(to_char(sysdate,'yyyyMMdd'),#{fpqqlsh,jdbcType=VARCHAR},#{ghfmc,jdbcType=VARCHAR},#{ghf_nsrsbh,jdbcType=VARCHAR},#{ghfqylx,jdbcType=VARCHAR},#{ghf_yhzh,jdbcType=VARCHAR},#{ghf_dz,jdbcType=VARCHAR},#{ghf_sh,jdbcType=VARCHAR},#{ghf_gddh,jdbcType=VARCHAR},#{ghf_sj,jdbcType=VARCHAR},#{ghf_email,jdbcType=VARCHAR},#{ghf_dzdh,jdbcType=VARCHAR},#{xhf_dzdh,jdbcType=VARCHAR},#{xhf_nsrsbh,jdbcType=VARCHAR},#{xhfmc,jdbcType=VARCHAR},#{xhf_dz,jdbcType=VARCHAR},#{xhf_dh,jdbcType=VARCHAR},#{xhf_yhzh,jdbcType=VARCHAR},#{yfp_dm,jdbcType=VARCHAR},#{yfp_hm,jdbcType=VARCHAR},#{kpy,jdbcType=VARCHAR},#{sky,jdbcType=VARCHAR},#{fhr,jdbcType=VARCHAR},#{qd_bz,jdbcType=VARCHAR},#{kplx,jdbcType=VARCHAR},#{kphjje,jdbcType=VARCHAR},#{hjbhsje,jdbcType=VARCHAR},#{hjse,jdbcType=VARCHAR},#{spmc,jdbcType=VARCHAR},#{sm,jdbcType=VARCHAR},#{sl,jdbcType=VARCHAR},#{ggxh,jdbcType=VARCHAR},#{jldw,jdbcType=VARCHAR},#{spsl,jdbcType=VARCHAR},#{spdj,jdbcType=VARCHAR},#{spje,jdbcType=VARCHAR},#{fphxz,jdbcType=VARCHAR},#{hsbz,jdbcType=VARCHAR},#{se,jdbcType=VARCHAR},#{spbm,jdbcType=VARCHAR},#{zxbm,jdbcType=VARCHAR},#{yhzcbs,jdbcType=VARCHAR},#{lslbs,jdbcType=VARCHAR},#{zzstsgl,jdbcType=VARCHAR},#{tsfs,jdbcType=VARCHAR},#{fplx,jdbcType=VARCHAR},#{sjly,jdbcType=VARCHAR},#{dkbz,jdbcType=VARCHAR},#{bmb_bbh,jdbcType=VARCHAR},#{txcsbz,jdbcType=VARCHAR},#{qdxmmc,jdbcType=VARCHAR},#{jqbh,jdbcType=VARCHAR},#{returnCode,jdbcType=VARCHAR},#{returnMessage,jdbcType=VARCHAR},#{kprq,jdbcType=VARCHAR},#{ssyf,jdbcType=VARCHAR},#{fp_dm,jdbcType=VARCHAR},#{fp_hm,jdbcType=VARCHAR},#{retcode,jdbcType=VARCHAR},#{fwmw,jdbcType=VARCHAR},#{jym,jdbcType=VARCHAR},#{szqm,jdbcType=VARCHAR},#{ewm,jdbcType=VARCHAR},#{returnCodeQZ,jdbcType=VARCHAR},#{returnMessageQZ,jdbcType=VARCHAR},#{pdf_file,jdbcType=VARCHAR},#{pdf_url,jdbcType=VARCHAR},#{status,jdbcType=VARCHAR},to_char(sysdate,'yyyyMMddHH24MISS'))")
    void insertFPInfo(fpInfo fpinfo);


    @Insert("insert into t_dtl_fptxn(fpqqlsh,txn_src,inntype,cardno,txndate,txnamt,censeq,fp_status)values(#{fpqqlsh,jdbcType=VARCHAR},#{txn_src,jdbcType=VARCHAR},#{inntype,jdbcType=VARCHAR},#{cardno,jdbcType=VARCHAR},#{txndate,jdbcType=VARCHAR},#{txnamt,jdbcType=VARCHAR},#{ref_key,jdbcType=VARCHAR},#{fp_status,jdbcType=VARCHAR})")
    void insertFpTxnInfo(fpInfo fpinfo);


    //@Update("update  T_DTL_FPINFO t set t.status='2' where t.fp_dm = #{yfp_dm,jdbcType=VARCHAR} and t.fp_hm = #{yfp_hm,jdbcType=VARCHAR} and t.kplx='0'")
    //void updateByYFPInfo(@Param("yfp_dm")String yfp_dm,@Param("yfp_hm")String yfp_hm);


    @Update("update  T_DTL_FPINFO t set t.returnCodeQZ=#{returnCodeQZ,jdbcType=VARCHAR} ,returnMessageQZ=#{returnMessageQZ,jdbcType=VARCHAR},pdf_file=#{pdf_file,jdbcType=VARCHAR},pdf_url=#{pdf_url,jdbcType=VARCHAR},t.status='1' where t.fpqqlsh = #{fpqqlsh,jdbcType=VARCHAR}")
    void updateFPInfo(fpInfo fpinfo);

    @Update("update  t_dtl_fptxn t set t.fp_status='1' where t.fpqqlsh = #{fpqqlsh,jdbcType=VARCHAR}")
    void updateFpTxnInfo(fpInfo fpinfo);

    @Update("update  t_dtl_cardsv t set t.billstatus='1',billtxndate=to_char(sysdate,'yyyyMMdd'),billtxntime=to_char(sysdate,'HH24MISS')，fp_hm=#{fp_hm,jdbcType=VARCHAR},fp_dm=#{fp_dm,jdbcType=VARCHAR}  where t.cardno = #{cardno,jdbcType=VARCHAR} and ref_key=#{ref_key,jdbcType=VARCHAR}")
    void updateCardsvInfo(@Param("cardno") String cardno, @Param("ref_key") String ref_key, @Param("fp_hm") String fp_hm, @Param("fp_dm") String fp_dm);

    /*
    @Select("SELECT fpqqlsh,ghfmc,ghf_nsrsbh ,ghfqylx,ghf_yhzh,ghf_dz,ghf_sh ,ghf_gddh,ghf_sj,ghf_email,ghf_dzdh,xhf_dzdh,xhf_nsrsbh,xhfmc,xhf_dz,xhf_dh,xhf_yhzh,yfp_dm,yfp_hm ,kpy,sky,fhr,qd_bz,kplx,to_char(kphjje,'fm9999990.0999') kphjje,to_char(hjbhsje,'fm9999990.0999') hjbhsje,to_char(hjse,'fm9999990.0999') hjse,spmc,sm,to_char(sl,'fm9999990.0999') sl,ggxh,jldw ,spsl,to_char(spdj,'fm9999990.0999') spdj,to_char(spje,'fm9999990.0999') spje,fphxz,hsbz,to_char(se,'fm9999990.0999') se,spbm,zxbm,yhzcbs,lslbs,zzstsgl,tsfs,fplx,sjly,dkbz,bmb_bbh,txcsbz,qdxmmc,fp_dm,fp_hm FROM T_DTL_FPINFO t WHERE t.fp_dm = #{yfp_dm,jdbcType=VARCHAR} and fp_hm = #{yfp_hm,jdbcType=VARCHAR} and kplx='0'")
    @Results({
            @Result(property = "fpqqlsh", column = "fpqqlsh"),
            @Result(property = "ghfmc", column = "ghfmc"),
            @Result(property = "ghf_nsrsbh", column = "ghf_nsrsbh"),
            @Result(property = "ghfqylx", column = "ghfqylx"),
            @Result(property = "ghf_yhzh", column = "ghf_yhzh"),
            @Result(property = "ghf_dz", column = "ghf_dz"),
            @Result(property = "ghf_sh", column = "ghf_sh"),
            @Result(property = "ghf_gddh", column = "ghf_gddh"),
            @Result(property = "ghf_sj", column = "ghf_sj"),
            @Result(property = "ghf_email", column = "ghf_email"),
            @Result(property = "ghf_dzdh", column = "ghf_dzdh"),
            @Result(property = "xhf_dzdh", column = "xhf_dzdh"),
            @Result(property = "xhf_nsrsbh", column = "xhf_nsrsbh"),
            @Result(property = "xhfmc", column = "xhfmc"),
            @Result(property = "xhf_dz", column = "xhf_dz"),
            @Result(property = "xhf_dh", column = "xhf_dh"),
            @Result(property = "xhf_yhzh", column = "xhf_yhzh"),
            @Result(property = "yfp_dm", column = "yfp_dm"),
            @Result(property = "yfp_hm ", column = "yfp_hm"),
            @Result(property = "kpy", column = "kpy"),
            @Result(property = "sky", column = "sky"),
            @Result(property = "fhr", column = "fhr"),
            @Result(property = "qd_bz", column = "qd_bz"),
            @Result(property = "kplx", column = "kplx"),
            @Result(property = "kphjje", column = "kphjje"),
            @Result(property = "hjbhsje", column = "hjbhsje"),
            @Result(property = "hjse", column = "hjse"),
            @Result(property = "spmc", column = "spmc"),
            @Result(property = "sm", column = "sm"),
            @Result(property = "sl", column = "sl"),
            @Result(property = "ggxh", column = "ggxh"),
            @Result(property = "jldw", column = "jldw"),
            @Result(property = "spsl", column = "spsl"),
            @Result(property = "spdj", column = "spdj"),
            @Result(property = "spje", column = "spje"),
            @Result(property = "fphxz", column = "fphxz"),
            @Result(property = "hsbz", column = "hsbz"),
            @Result(property = "se", column = "se"),
            @Result(property = "spbm", column = "spbm"),
            @Result(property = "zxbm ", column = "zxbm"),
            @Result(property = "yhzcbs", column = "yhzcbs"),
            @Result(property = "lslbs", column = "lslbs"),
            @Result(property = "zzstsgl", column = "zzstsgl"),
            @Result(property = "tsfs", column = "tsfs"),
            @Result(property = "fplx", column = "fplx"),
            @Result(property = "sjly", column = "sjly"),
            @Result(property = "dkbz", column = "dkbz"),
            @Result(property = "bmb_bbh", column = "bmb_bbh"),
            @Result(property = "txcsbz", column = "txcsbz"),
            @Result(property = "qdxmmc", column = "qdxmmc"),
            @Result(property = "fp_dm", column = "fp_dm"),
            @Result(property = "fp_hm", column = "fp_hm"),

    })
    fpInfo selectByYFPInfo(@Param("yfp_dm") String yfp_dm, @Param("yfp_hm") String yfp_hm);
    */

    @Select("SELECT t.fpqqlsh,b.inntype,b.cardno,b.txndate,b.txnamt,b.censeq,b.fp_status,t.ghfmc,t.ghf_nsrsbh ,t.ghfqylx,t.ghf_yhzh,t.ghf_dz,t.ghf_sh ,t.ghf_gddh,t.ghf_sj,t.ghf_email,t.ghf_dzdh,t.xhf_dzdh,t.xhf_nsrsbh,t.xhfmc,t.xhf_dz,t.xhf_dh,t.xhf_yhzh,t.yfp_dm,t.yfp_hm ,t.kpy,t.sky,t.fhr,t.qd_bz,t.kplx,to_char(t.kphjje,'fm9999990.0999') kphjje,to_char(t.hjbhsje,'fm9999990.0999') hjbhsje,to_char(t.hjse,'fm9999990.0999') hjse,t.spmc,t.sm,to_char(t.sl,'fm9999990.0999') sl,t.ggxh,t.jldw ,t.spsl,to_char(t.spdj,'fm9999990.0999') spdj,to_char(t.spje,'fm9999990.0999') spje,t.fphxz,t.hsbz,to_char(t.se,'fm9999990.0999') se,t.spbm,t.zxbm,t.yhzcbs,t.lslbs,t.zzstsgl,t.tsfs,t.fplx,t.sjly,t.dkbz,t.bmb_bbh,t.txcsbz,t.qdxmmc,t.jqbh,t.returncode,t.returnmessage,t.kprq,t.ssyf,t.fp_dm,t.fp_hm,t.retcode,t.fwmw,t.jym,t.szqm,t.ewm FROM T_DTL_FPINFO t,t_dtl_fptxn b WHERE t.fpqqlsh=b.fpqqlsh and b.cardno = #{cardno,jdbcType=VARCHAR} and censeq = #{ref_key,jdbcType=VARCHAR} and returncode='0000'")
    @Results({

            @Result(property = "fpqqlsh", column = "fpqqlsh"),
            @Result(property = "inntype", column = "txnamt"),
            @Result(property = "cardno", column = "cardno"),
            @Result(property = "txndate", column = "txndate"),
            @Result(property = "txnamt", column = "txnamt"),
            @Result(property = "ref_key", column = "censeq"),
            @Result(property = "fp_status", column = "fp_status"),
            @Result(property = "ghfmc", column = "ghfmc"),
            @Result(property = "ghf_nsrsbh", column = "ghf_nsrsbh"),
            @Result(property = "ghfqylx", column = "ghfqylx"),
            @Result(property = "ghf_yhzh", column = "ghf_yhzh"),
            @Result(property = "ghf_dz", column = "ghf_dz"),
            @Result(property = "ghf_sh", column = "ghf_sh"),
            @Result(property = "ghf_gddh", column = "ghf_gddh"),
            @Result(property = "ghf_sj", column = "ghf_sj"),
            @Result(property = "ghf_email", column = "ghf_email"),
            @Result(property = "ghf_dzdh", column = "ghf_dzdh"),
            @Result(property = "xhf_dzdh", column = "xhf_dzdh"),
            @Result(property = "xhf_nsrsbh", column = "xhf_nsrsbh"),
            @Result(property = "xhfmc", column = "xhfmc"),
            @Result(property = "xhf_dz", column = "xhf_dz"),
            @Result(property = "xhf_dh", column = "xhf_dh"),
            @Result(property = "xhf_yhzh", column = "xhf_yhzh"),
            @Result(property = "yfp_dm", column = "yfp_dm"),
            @Result(property = "yfp_hm ", column = "yfp_hm"),
            @Result(property = "kpy", column = "kpy"),
            @Result(property = "sky", column = "sky"),
            @Result(property = "fhr", column = "fhr"),
            @Result(property = "qd_bz", column = "qd_bz"),
            @Result(property = "kplx", column = "kplx"),
            @Result(property = "kphjje", column = "kphjje"),
            @Result(property = "hjbhsje", column = "hjbhsje"),
            @Result(property = "hjse", column = "hjse"),
            @Result(property = "spmc", column = "spmc"),
            @Result(property = "sm", column = "sm"),
            @Result(property = "sl", column = "sl"),
            @Result(property = "ggxh", column = "ggxh"),
            @Result(property = "jldw", column = "jldw"),
            @Result(property = "spsl", column = "spsl"),
            @Result(property = "spdj", column = "spdj"),
            @Result(property = "spje", column = "spje"),
            @Result(property = "fphxz", column = "fphxz"),
            @Result(property = "hsbz", column = "hsbz"),
            @Result(property = "se", column = "se"),
            @Result(property = "spbm", column = "spbm"),
            @Result(property = "zxbm ", column = "zxbm"),
            @Result(property = "yhzcbs", column = "yhzcbs"),
            @Result(property = "lslbs", column = "lslbs"),
            @Result(property = "zzstsgl", column = "zzstsgl"),
            @Result(property = "tsfs", column = "tsfs"),
            @Result(property = "fplx", column = "fplx"),
            @Result(property = "sjly", column = "sjly"),
            @Result(property = "dkbz", column = "dkbz"),
            @Result(property = "bmb_bbh", column = "bmb_bbh"),
            @Result(property = "txcsbz", column = "txcsbz"),
            @Result(property = "qdxmmc", column = "qdxmmc"),

            @Result(property = "jqbh", column = "jqbh"),
            @Result(property = "returnCode", column = "returncode"),
            @Result(property = "returnMessage", column = "returnmessage"),
            @Result(property = "kprq", column = "kprq"),
            @Result(property = "ssyf", column = "ssyf"),
            @Result(property = "fp_dm", column = "fp_dm"),
            @Result(property = "fp_hm", column = "fp_hm"),
            @Result(property = "retcode", column = "retcode"),
            @Result(property = "fwmw", column = "fwmw"),
            @Result(property = "jym", column = "jym"),
            @Result(property = "szqm", column = "szqm"),
            @Result(property = "ewm", column = "ewm"),

    })
    fpInfo selectFPInfo(@Param("cardno") String cardno, @Param("ref_key") String ref_key);


    /*
    @Select("SELECT fpqqlsh,txn_src,inntype,cardno,txndate,txnamt,censeq,fp_status FROM t_dtl_fptxn t WHERE t.cardno = #{cardno,jdbcType=VARCHAR} and censeq = #{ref_key,jdbcType=VARCHAR} and t.txn_src='10' and t.fp_status='1'")
    @Results({
            @Result(property = "fpqqlsh", column = "fpqqlsh"),
            @Result(property = "txn_src", column = "txn_src"),
            @Result(property = "inntype", column = "txnamt"),
            @Result(property = "cardno", column = "cardno"),
            @Result(property = "txndate", column = "txndate"),
            @Result(property = "txnamt", column = "txnamt"),
            @Result(property = "censeq", column = "ghfmc"),
            @Result(property = "fp_status", column = "fp_status"),

    })
    fpInfo selectFPTxnInfo(@Param("cardno") String cardno, @Param("ref_key") String ref_key);
    */

    @Select("SELECT cardno,orgamt,ref_key,inntype,txndate,nvl(rsvd5,'N') as cancelflag,billstatus  from t_dtl_cardsv t where  t.cardno = #{cardno,jdbcType=VARCHAR}  and t.ref_key = #{ref_key,jdbcType=VARCHAR}")
    @Results({
            @Result(property = "cardno", column = "cardno"),
            @Result(property = "txnamt", column = "orgamt"),
            @Result(property = "ref_key", column = "ref_key"),
            @Result(property = "inntype", column = "inntype"),
            @Result(property = "txndate", column = "txndate"),
            @Result(property = "cancelflag", column = "cancelflag"),
            @Result(property = "fp_status", column = "billstatus"),

    })
    fpInfo selectTxnData(@Param("cardno") String cardno, @Param("ref_key") String ref_key);

}
