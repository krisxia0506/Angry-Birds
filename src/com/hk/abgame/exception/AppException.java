package com.hk.abgame.exception;

import lombok.Data;

/**
 * Created on 2022-07-06 9:59
 *
 * @author Xia Jiayi
 */

public class AppException extends Exception {
    private int errorCode;
    private String errorMsg;
    public AppException() {
        super();
    }

    public AppException(int errorCode, String errorMsg) {
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

