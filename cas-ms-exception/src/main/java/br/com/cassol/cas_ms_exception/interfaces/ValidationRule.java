package br.com.cassol.cas_ms_exception.interfaces;

import br.com.cassol.cas_ms_exception.exception.errors.CustomError;

public interface ValidationRule<T> {
	boolean isValid(T entity);

	CustomError getError();
}