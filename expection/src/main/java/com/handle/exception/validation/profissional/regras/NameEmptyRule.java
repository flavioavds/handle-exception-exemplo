package com.handle.exception.validation.profissional.regras;

import com.handle.exception.entity.Profissional;
import com.handle.exception.exception.constants.ErrorMessages;

import br.com.cassol.cas_ms_exception.exception.errors.CustomError;
import br.com.cassol.cas_ms_exception.interfaces.ValidationRule;

public class NameEmptyRule implements ValidationRule<Profissional> {

	@Override
	public boolean isValid(Profissional profissional) {
		return profissional.getName() != null && !profissional.getName().isEmpty();
	}

	@Override
	public CustomError getError() {
		return new CustomError("name", ErrorMessages.PROFESSIONAL_NAME_EMPTY);
	}
}