package com.wn.quickrechatge.httpresponse;

/**
 * Created by Administrator on 2016/7/1.
 */
public class BaseResponse {
    private boolean isSuccess;

    private int errorCode;

    private String msg;

    public boolean isSuccess() {
        return isSuccess;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
