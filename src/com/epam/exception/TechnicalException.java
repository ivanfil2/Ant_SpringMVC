package com.epam.exception;

public class TechnicalException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public TechnicalException() {
	}

	public TechnicalException(String message) {
		super(message);
	}

	public TechnicalException(String arg0, Exception arg1) {
		super(arg0, arg1);
	}

	public TechnicalException(Exception arg0) {
		super(arg0);
	}

}
