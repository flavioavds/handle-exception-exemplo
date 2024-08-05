package com.handle.exception.domain.loja.validations.rules;

import org.springframework.beans.factory.annotation.Autowired;

import com.handle.exception.domain.loja.Loja;
import com.handle.exception.domain.loja.LojaRepository;
import com.handle.exception.exception.constants.ErrorMessages;

import br.com.cassol.cas_ms_exception.exception.errors.CustomError;
import br.com.cassol.cas_ms_exception.interfaces.ValidationRule;

public class LojaNameExistsRule implements ValidationRule<Loja> {

	@Autowired
	private LojaRepository lojaRepository;

	@Override
	public boolean isValid(Loja loja) {
		return !this.lojaRepository.existsByName(loja.getName());
	}

	@Override
	public CustomError getError() {
		return new CustomError("name", ErrorMessages.LOJA_NAME_EXISTS);
	}

}
