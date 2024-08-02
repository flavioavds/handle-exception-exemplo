package com.handle.exception.validation.profissional;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.handle.exception.entity.Profissional;
import com.handle.exception.interfaces.LojaRepository;
import com.handle.exception.interfaces.ProfissionalRepository;
import com.handle.exception.validation.profissional.regras.CargoEmptyRule;
import com.handle.exception.validation.profissional.regras.CargoExistsRule;
import com.handle.exception.validation.profissional.regras.LojaExistsRule;
import com.handle.exception.validation.profissional.regras.NameEmptyRule;
import com.handle.exception.validation.profissional.regras.NameExistsRule;

import br.com.cassol.cas_ms_exception.exception.errors.CustomError;
import br.com.cassol.cas_ms_exception.interfaces.Validator;
import br.com.cassol.cas_ms_exception.validation.ValidationStep;

@Component
public class ProfissionalValidator implements Validator<Profissional> {

	private Validator<Profissional> next;
	private final ValidationStep<Profissional> validationStep = new ValidationStep<>();
	private Long lojaId;
	private final LojaRepository lojaRepository;

	public ProfissionalValidator(ProfissionalRepository profissionalRepository, LojaRepository lojaRepository) {
		this.lojaRepository = lojaRepository;
		this.validationStep.addRule(new NameExistsRule(profissionalRepository));
		this.validationStep.addRule(new NameEmptyRule());
		this.validationStep.addRule(new CargoExistsRule(profissionalRepository));
		this.validationStep.addRule(new CargoEmptyRule());
	}

	public void setLojaId(Long lojaId) {
		this.lojaId = lojaId;
	}

	@Override
	public void validate(Profissional profissional, List<CustomError> errors) {
		this.validateLoja(profissional).ifPresent(errors::add);
		this.validationStep.validate(profissional, errors);
		Optional.ofNullable(this.next).ifPresent(validator -> validator.validate(profissional, errors));
	}

	private Optional<CustomError> validateLoja(Profissional profissional) {
		return Optional.ofNullable(this.lojaId).map(id -> new LojaExistsRule(this.lojaRepository, id))
				.filter(rule -> !rule.isValid(profissional)).map(LojaExistsRule::getError);
	}

	@Override
	public Validator<Profissional> linkWith(Validator<Profissional> next) {
		this.next = next;
		return next;
	}
}