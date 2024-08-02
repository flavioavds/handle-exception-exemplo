package com.handle.exception.validation.profissional.regras;

import com.handle.exception.entity.Profissional;
import com.handle.exception.exception.constants.ErrorMessages;
import com.handle.exception.interfaces.ProfissionalRepository;

import br.com.cassol.cas_ms_exception.exception.errors.CustomError;
import br.com.cassol.cas_ms_exception.interfaces.ValidationRule;

public class CargoExistsRule implements ValidationRule<Profissional> {

	private final ProfissionalRepository profissionalRepository;

	public CargoExistsRule(ProfissionalRepository profissionalRepository) {
		this.profissionalRepository = profissionalRepository;
	}

	@Override
	public boolean isValid(Profissional profissional) {
		return !this.profissionalRepository.existsByCargo(profissional.getCargo());
	}

	@Override
	public CustomError getError() {
		return new CustomError("cargo", ErrorMessages.PROFESSIONAL_CARGO_EXISTE);
	}
}