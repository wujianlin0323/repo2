package com.jieyi.entity;

import java.io.Serializable;

/**
 * Created by Intellij IDEA.
 *
 * @Author LUOLIANG
 * @Date 2016/8/2
 * @Comment
 */
public class invoiceInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String headtype;

    private String headname;

    private String taxno;

    private String address;
    private String mobile;

    private String tel;

    private String email;

    private String bankname;

    private String accountno;
    private String id;

    public void setHeadtype(String headtype) {
        this.headtype = headtype;
    }

    public String getHeadtype() {
        return headtype;
    }


    public String getHeadname() {
        return headname;
    }

    public void setHeadname(String headname) {
        this.headname = headname;
    }

    public String getTaxno() {
        return taxno;
    }

    public void setTaxno(String taxno) {
        this.taxno = taxno;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }

    public String getAccountno() {
        return accountno;
    }

    public void setAccountno(String accountno) {
        this.accountno = accountno;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "receiptInfo{" +
                ", headtype='" + headtype + '\'' +
                ", headname='" + headname + '\'' +
                ", taxno='" + taxno + '\'' +
                ", address=" + address +
                ", tel='" + tel + '\'' +
                ", email='" + email + '\'' +
                ", bankname='" + bankname + '\'' +
                ", accountno='" + accountno + '\'' +
                '}';
    }
}