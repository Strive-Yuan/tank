package com.api.exception;

import org.apache.poi.ss.formula.functions.T;

public class ApiMessage {
    public static final ApiMessage OK = new ApiMessage(ErrorCode.SUCCESS, "成功！");

    public ErrorCode code;
    public String message;
    public T data;

    public ApiMessage(ErrorCode code) {
        this.code = code;
        this.message = code.name();
    }

    public ApiMessage(ErrorCode code, String message) {
        this.code = code;
        this.message = message;
    }

    public ApiMessage(ErrorCode code, T data) {
        this.code = code;
        this.data = data;
    }

    public static ApiMessage error(Throwable error) {
        return new ApiMessage(ErrorCode.ERROR, error.getMessage());
    }

    public static ApiMessage of(ErrorCode code, String message) {
        return new ApiMessage(code, message);
    }

    public static ApiMessage of(ErrorCode code) {
        return new ApiMessage(code, code.name());
    }
}
