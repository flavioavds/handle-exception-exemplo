package com.handle.exception.domain.loja.validations.rules;

import com.handle.exception.domain.loja.Loja;
import com.handle.exception.exception.constants.ErrorMessages;

import br.com.cassol.cas_ms_exception.exception.errors.CustomError;
import br.com.cassol.cas_ms_exception.interfaces.ValidationRule;

public class LojaEmptyRule implements ValidationRule<Loja> {

	@Override
	public boolean isValid(Loja loja) {
		return loja.getLoja() != null && loja.getLoja() != 0;
	}

	@Override
	public CustomError getError() {
		return new CustomError("loja", ErrorMessages.LOJA_NULL);
	}

}
