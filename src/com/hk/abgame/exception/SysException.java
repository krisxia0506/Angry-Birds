package com.hk.abgame.exception;

import lombok.Data;

/**
 * Created on 2022-07-06 9:44
 * 用于抛出系统异常的类
 * @author Xia Jiayi
 */

public class SysException extends Exception {
    private int errorCode;
    private String errorMsg;
    public SysException() {
        super();
    }

    public SysException(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}

