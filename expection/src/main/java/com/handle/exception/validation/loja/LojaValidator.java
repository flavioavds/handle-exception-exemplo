package com.handle.exception.validation.loja;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.handle.exception.entity.Profissional;
import com.handle.exception.validation.profissional.regras.CargoEmptyRule;
import com.handle.exception.validation.profissional.regras.CargoExistsRule;
import com.handle.exception.validation.profissional.regras.NameEmptyRule;
import com.handle.exception.validation.profissional.regras.NameExistsRule;

import br.com.cassol.cas_ms_exception.exception.errors.CustomError;
import br.com.cassol.cas_ms_exception.interfaces.Validator;
import br.com.cassol.cas_ms_exception.validation.ValidationStep;

@Component
public class LojaValidator implements Validator<Profissional> {

	private Validator<T> next;
	private final ValidationStep<Profissional> validationStep = new ValidationStep<>();

	public LojaValidator(Profissional profissional) {
		this.validationStep.addRule(new NameExistsRule(profissionalRepository));
		this.validationStep.addRule(new NameEmptyRule());
		this.validationStep.addRule(new CargoExistsRule(profissionalRepository));
		this.validationStep.addRule(new CargoEmptyRule());
	}

	@Override
	public void validate(Profissional profissional, List<CustomError> errors) {
		this.validateLoja(profissional).ifPresent(errors::add);
		this.validationStep.validate(profissional, errors);
		Optional.ofNullable(this.next).ifPresent(validator -> validator.validate(profissional, errors));
	}

	@Override
	public Validator<Profissional> linkWith(Validator<Profissional> next) {
		this.next = next;
		return next;
	}
}