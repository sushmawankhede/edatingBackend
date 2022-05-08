package com.edating.exceptions;

public class UserNotMatchException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UserNotMatchException() {
		super();
	}

	public UserNotMatchException(String message) {
		super(message);
	}

}
