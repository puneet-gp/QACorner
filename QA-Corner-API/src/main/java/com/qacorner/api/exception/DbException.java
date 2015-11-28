package com.qacorner.api.exception;

public class DbException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public DbException() {
		super();
	}
	
	public DbException(String message) {
		super(message);
	}
	
	public DbException(String message, Throwable t) {
		super(message, t);
	}
	
	public DbException(Throwable t) {
		super(t);
	}
}