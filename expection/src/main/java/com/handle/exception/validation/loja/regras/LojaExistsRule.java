package com.handle.exception.validation.loja.regras;

import com.handle.exception.entity.Profissional;
import com.handle.exception.exception.constants.ErrorMessages;
import com.handle.exception.interfaces.LojaRepository;

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