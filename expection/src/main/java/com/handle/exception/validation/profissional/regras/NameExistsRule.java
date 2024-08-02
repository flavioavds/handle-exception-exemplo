package com.handle.exception.validation.profissional.regras;

import com.handle.exception.entity.Profissional;
import com.handle.exception.exception.constants.ErrorMessages;
import com.handle.exception.interfaces.ProfissionalRepository;

import br.com.cassol.cas_ms_exception.exception.errors.CustomError;
import br.com.cassol.cas_ms_exception.interfaces.ValidationRule;

public class NameExistsRule implements ValidationRule<Profissional> {

	private final ProfissionalRepository profissionalRepository;

	public NameExistsRule(ProfissionalRepository profissionalRepository) {
		this.profissionalRepository = profissionalRepository;
	}

	@Override
	public boolean isValid(Profissional profissional) {
		return !this.profissionalRepository.existsByName(profissional.getName());
	}

	@Override
	public CustomError getError() {
		return new CustomError("name", ErrorMessages.PROFESSIONAL_NAME_EXISTE);
	}
}