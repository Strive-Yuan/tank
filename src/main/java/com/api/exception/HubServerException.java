package com.api.exception;

import com.api.response.ResponseEnum;

public class HubServerException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private Object object;

	private ResponseEnum responseEnum;

	public HubServerException(String msg) {
		super(msg);
	}

	public HubServerException(String msg, Object object) {
		super(msg);
		this.object = object;
	}

	public HubServerException(String msg, Throwable cause) {
		super(msg, cause);
	}


	public HubServerException(ResponseEnum responseEnum) {
		super(responseEnum.getMsg());
		this.responseEnum = responseEnum;
	}

	public HubServerException(ResponseEnum responseEnum, Object object) {
		super(responseEnum.getMsg());
		this.responseEnum = responseEnum;
		this.object = object;
	}


	public Object getObject() {
		return object;
	}

	public ResponseEnum getResponseEnum() {
		return responseEnum;
	}

}
