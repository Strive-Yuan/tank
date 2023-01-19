package com.api.exception;

//定义全局异常类 继承Exception类/其相对应的异常类；
public class ApiException extends Exception{

    //定义异常类属性，一般包括错误代码和错误信息；
    private ErrorCode errorCode;
    private Object errorMsg;

    public ApiException(ErrorCode errorCode, Object errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    //get、set方法；
    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public Object getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(Object errorMsg) {
        this.errorMsg = errorMsg;
    }
}