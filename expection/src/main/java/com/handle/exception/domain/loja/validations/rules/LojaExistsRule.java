package com.handle.exception.domain.loja.validations.rules;

import com.handle.exception.domain.loja.LojaRepository;
import com.handle.exception.domain.profissional.Profissional;
import com.handle.exception.exception.constants.ErrorMessages;

import br.com.cassol.cas_ms_exception.exception.errors.CustomError;
import br.com.cassol.cas_ms_exception.interfaces.ValidationRule;

public class LojaExistsRule implements ValidationRule<Profissional> {

	private final LojaRepository lojaRepository;
	private final Long lojaId;

	public LojaExistsRule(LojaRepository lojaRepository, Long lojaId) {
		this.lojaRepository = lojaRepository;
		this.lojaId = lojaId;
	}

	@Override
	public boolean isValid(Profissional profissional) {
		return this.lojaRepository.findById(this.lojaId).map(loja -> {
			profissional.setLoja(loja);
			return true;
		}).orElse(false);
	}

	@Override
	public CustomError getError() {
		return new CustomError("loja", ErrorMessages.LOJA_ERROR + this.lojaId);
	}
}