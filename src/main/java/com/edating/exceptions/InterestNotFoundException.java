package com.edating.exceptions;

public class InterestNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InterestNotFoundException() {
		super();
	}

	public InterestNotFoundException(String message) {
		super(message);
	}

}
