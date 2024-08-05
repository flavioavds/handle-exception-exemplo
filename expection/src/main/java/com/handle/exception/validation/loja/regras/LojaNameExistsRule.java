package com.handle.exception.validation.loja.regras;

import com.handle.exception.entity.Loja;
import com.handle.exception.exception.constants.ErrorMessages;
import com.handle.exception.interfaces.LojaRepository;

import br.com.cassol.cas_ms_exception.exception.errors.CustomError;
import br.com.cassol.cas_ms_exception.interfaces.ValidationRule;

public class LojaNameExistsRule implements ValidationRule<Loja> {

	private final LojaRepository lojaRepository;

	public LojaNameExistsRule(LojaRepository lojaRepository) {
		this.lojaRepository = lojaRepository;
	}

	@Override
	public boolean isValid(Loja loja) {
		return !this.lojaRepository.existsByName(loja.getName());
	}

	@Override
	public CustomError getError() {
		return new CustomError("name", ErrorMessages.LOJA_NAME_EXISTS);
	}

}
