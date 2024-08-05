package br.com.cassol.cas_ms_exception.interfaces;

import org.springframework.stereotype.Component;

import br.com.cassol.cas_ms_exception.exception.errors.CustomError;

public interface ValidationRule<T> {
	boolean isValid(T entity);
	CustomError getError();
}