package com.api.exception;

public enum ErrorCode {
    SUCCESS("200"),
    NOT_FOUNT("404"),
    ERROR("500");
    private String code;

    ErrorCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
