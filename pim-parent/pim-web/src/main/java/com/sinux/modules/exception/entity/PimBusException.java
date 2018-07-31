package com.sinux.modules.exception.entity;

import java.io.Serializable;

/**
 * @author lf
 * @Title: PimBusException
 * @Description: 业务异常实体
 * @date 2018/7/17 15:18
 */
public class PimBusException extends RuntimeException implements Serializable {

    private int errorCode;
    private String errorMsg;

    public PimBusException(){
        this.errorCode = 666666;
        this.errorMsg = "默认错误消息";
    }

    public PimBusException(String errorMsg) {
        this.errorCode = 666666;
        this.errorMsg = errorMsg;
    }

    public PimBusException(int errorCode, String errorMsg) {
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

    @Override
    public String toString() {
        return "PimBusException{" +
                "errorCode=" + errorCode +
                ", errorMsg='" + errorMsg + '\'' +
                '}';
    }
}
