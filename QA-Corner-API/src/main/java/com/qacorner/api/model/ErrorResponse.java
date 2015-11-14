package com.qacorner.api.model;

public class ErrorResponse {

	private String errorMsg;
	private int statusCode;

	public ErrorResponse() {
	}

	public ErrorResponse(String errorMsg, int statusCode) {
		super();
		this.errorMsg = errorMsg;
		this.statusCode = statusCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

}
