package com.handle.exception.domain.profissional.validations.rules;

import com.handle.exception.domain.profissional.Profissional;
import com.handle.exception.exception.constants.ErrorMessages;

import br.com.cassol.cas_ms_exception.exception.errors.CustomError;
import br.com.cassol.cas_ms_exception.interfaces.ValidationRule;

public class CargoEmptyRule implements ValidationRule<Profissional> {

	@Override
	public boolean isValid(Profissional profissional) {
		return profissional.getCargo() != null && !profissional.getCargo().isEmpty();
	}

	@Override
	public CustomError getError() {
		return new CustomError("cargo", ErrorMessages.CARGO_NAME_EMPTY);
	}
}