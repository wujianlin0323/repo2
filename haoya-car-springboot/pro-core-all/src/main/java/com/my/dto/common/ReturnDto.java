package com.my.dto.common;

/**
 * Created by TXW55 on 2018.9.4
 */
public class ReturnDto {

    /**
     * 是否成功
     */
    private boolean success;
    /**
     * 错误信息
     */
    private String msg;
    /**
     * 状态码
     */
    private String resCode;
    /**
     * 返回结果
     */
    private Object result;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getResCode() {
        return resCode;
    }

    public void setResCode(String resCode) {
        this.resCode = resCode;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

}
