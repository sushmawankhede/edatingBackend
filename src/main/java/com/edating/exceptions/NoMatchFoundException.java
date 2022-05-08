package com.edating.exceptions;

public class NoMatchFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NoMatchFoundException() {
		super();
	}

	public NoMatchFoundException(String message) {
		super(message);
	}

}
