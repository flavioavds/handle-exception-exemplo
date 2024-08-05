package br.com.cassol.cas_ms_exception.validation;

import java.util.ArrayList;
import java.util.List;

import br.com.cassol.cas_ms_exception.exception.errors.CustomError;

public class ValidationResult {

	private boolean valid;
	private List<CustomError> errors;

	private ValidationResult(boolean valid, List<CustomError> errors) {
		this.valid = valid;
		this.errors = errors;
	}

	public static ValidationResult valid() {
		return new ValidationResult(true, new ArrayList<>());
	}

	public static ValidationResult invalid(List<CustomError> errors) {
		return new ValidationResult(false, errors);
	}

	public boolean isValid() {
		return this.valid;
	}

	public List<CustomError> getErrors() {
		return this.errors;
	}

	public void addErrors(List<CustomError> errors) {
		this.errors.addAll(errors);
	}
}