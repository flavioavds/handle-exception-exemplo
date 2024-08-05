package br.com.cassol.cas_ms_exception.interfaces;

import java.util.ArrayList;

import br.com.cassol.cas_ms_exception.exception.errors.CustomError;
import br.com.cassol.cas_ms_exception.exception.errors.CustomException;

public interface Validator<T> {
	Validator<T> withExtraValidator(Validator<T> next);
	void validate() throws CustomException;
	ArrayList<CustomError> getErrors();
}
