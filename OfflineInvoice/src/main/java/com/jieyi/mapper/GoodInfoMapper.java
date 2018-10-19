package com.jieyi.mapper;

import com.jieyi.entity.t_bse_info_goods;
import org.apache.ibatis.annotations.*;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by yuyong on 2018/6/3.
 */
@Mapper
@Transactional
public interface GoodInfoMapper {

    @Select("select inntype,goods_id,goods_name,to_char(tax_rate ,'fm9999990.00') as tax_rate,model,cal_unit,unit_price,tax_sign,tax_name from  T_BSE_INFO_GOODS t where t.inntype = #{inntype} and t.check_flag ='1'")
    @Results({
            @Result(property = "inntype", column = "inntype"),
            @Result(property = "goods_id", column = "goods_id"),
            @Result(property = "goods_name", column = "goods_name"),
            @Result(property = "tax_rate", column = "tax_rate"),
            @Result(property = "model", column = "model"),
            @Result(property = "cal_unit", column = "cal_unit"),
            @Result(property = "unit_price", column = "unit_price"),
            @Result(property = "tax_sign", column = "tax_sign"),
            @Result(property = "tax_name", column = "tax_name"),
    })
    t_bse_info_goods selectByInntype(String inntype);
}
