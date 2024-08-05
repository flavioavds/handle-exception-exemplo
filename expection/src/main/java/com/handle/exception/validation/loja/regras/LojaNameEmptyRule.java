package com.handle.exception.validation.loja.regras;

import com.handle.exception.entity.Loja;
import com.handle.exception.exception.constants.ErrorMessages;

import br.com.cassol.cas_ms_exception.exception.errors.CustomError;
import br.com.cassol.cas_ms_exception.interfaces.ValidationRule;

public class LojaNameEmptyRule implements ValidationRule<Loja> {

	@Override
	public boolean isValid(Loja loja) {
		return loja.getName() != null && !loja.getName().isEmpty();
	}

	@Override
	public CustomError getError() {
		return new CustomError("nome", ErrorMessages.LOJA_NAME_NULL_EMPTY);
	}

}
