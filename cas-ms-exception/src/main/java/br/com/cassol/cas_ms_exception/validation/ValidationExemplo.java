package br.com.cassol.cas_ms_exception.validation;

import java.util.List;

import br.com.cassol.cas_ms_exception.exception.errors.CustomError;

public abstract class ValidationExemplo<T> {
	protected ValidationExemplo<T> next;

	public ValidationExemplo<T> linkWith(ValidationExemplo<T> next) {
		this.next = next;
		return next;
	}

	public abstract void validate(T request, List<CustomError> errors);

	protected void checkNext(T request, List<CustomError> errors) {
		if (this.next != null) {
			this.next.validate(request, errors);
		}
	}
}