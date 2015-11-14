package com.qacorner.api.model;

public class BasicSuccessResponse {

	private final static String DEFAULT_MESSAGE = "OK";
	private String message;
	private int statusCode;

	public BasicSuccessResponse(String message, int statusCode) {
		super();
		this.message = message;
		this.statusCode = statusCode;
	}

	public BasicSuccessResponse() {
		this.message = DEFAULT_MESSAGE;
		this.statusCode = 200;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
}
