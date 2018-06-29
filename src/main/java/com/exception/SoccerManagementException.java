package com.exception;

public class SoccerManagementException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SoccerManagementException() {
		super();
	}

	public SoccerManagementException(String message) {
		super(message);
	}

	public SoccerManagementException(String message, Throwable cause) {
		super(message, cause);
	}

	public SoccerManagementException(Throwable cause) {
		super(cause);
	}

}
