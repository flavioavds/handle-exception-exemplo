package br.com.cassol.cas_ms_exception.exception.errors;

import java.util.List;

public class CustomException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private final List<CustomError> errors;

	public CustomException(List<CustomError> errors) {
		super("Validation failed");
		this.errors = errors;
	}

	public List<CustomError> getErrors() {
		return this.errors;
	}
}