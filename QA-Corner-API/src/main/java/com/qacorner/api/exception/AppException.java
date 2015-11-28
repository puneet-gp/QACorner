package com.qacorner.api.exception;

public class AppException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public AppException() {
		super();
	}
	
	public AppException(String msg) {
		super(msg);
	}
	
	public AppException(String msg, Throwable t) {
		super(msg, t);
	}
	
	public AppException(Throwable t) {
		super(t);
	}
}
