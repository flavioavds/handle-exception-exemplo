package com.handle.exception.validation.profissional.regras;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.handle.exception.entity.Profissional;
import com.handle.exception.exception.constants.ErrorMessages;
import com.handle.exception.interfaces.ProfissionalRepository;

import br.com.cassol.cas_ms_exception.exception.errors.CustomError;
import br.com.cassol.cas_ms_exception.interfaces.ValidationRule;

@Component
public class NameExistsRule implements ValidationRule<Profissional> {

	@Autowired
	private ProfissionalRepository profissionalRepository;

	@Override
	public boolean isValid(Profissional profissional) {
		return !this.profissionalRepository.existsByName(profissional.getName());
	}

	@Override
	public CustomError getError() {
		return new CustomError("name", ErrorMessages.PROFESSIONAL_NAME_EXISTE);
	}
}