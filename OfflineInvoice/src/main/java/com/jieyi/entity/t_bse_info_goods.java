package com.jieyi.entity;

import java.io.Serializable;

public class t_bse_info_goods implements Serializable {

    private static final long serialVersionUID = 1L;

    private String inntype;
    private String goods_id;
    private String goods_name;
    private String tax_rate;
    private String model;
    private String cal_unit;
    private String unit_price;
    private String tax_sign;
    private String tax_name;

    public String getInntype() {
        return inntype;
    }

    public void setInntype(String inntype) {
        this.inntype = inntype;
    }

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getTax_rate() {
        return tax_rate;
    }

    public void setTax_rate(String tax_rate) {
        this.tax_rate = tax_rate;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCal_unit() {
        return cal_unit;
    }

    public void setCal_unit(String cal_unit) {
        this.cal_unit = cal_unit;
    }

    public String getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(String unit_price) {
        this.unit_price = unit_price;
    }

    public String getTax_sign() {
        return tax_sign;
    }

    public void setTax_sign(String tax_sign) {
        this.tax_sign = tax_sign;
    }

    public String getTax_name() {
        return tax_name;
    }

    public void setTax_name(String tax_name) {
        this.tax_name = tax_name;
    }
}
