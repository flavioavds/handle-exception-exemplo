package com.handle.exception.validation.profissional;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.handle.exception.entity.Profissional;
import com.handle.exception.validation.profissional.regras.CargoEmptyRule;
import com.handle.exception.validation.profissional.regras.CargoExistsRule;
import com.handle.exception.validation.profissional.regras.NameEmptyRule;
import com.handle.exception.validation.profissional.regras.NameExistsRule;

import br.com.cassol.cas_ms_exception.exception.errors.CustomError;
import br.com.cassol.cas_ms_exception.exception.errors.CustomException;
import br.com.cassol.cas_ms_exception.interfaces.Validator;
import br.com.cassol.cas_ms_exception.validation.ValidationStep;
import lombok.Getter;
import lombok.Setter;

@Component
public class ProfissionalValidator implements Validator<Object> {

	private Profissional profissional;
	private Validator<Object> next;
	@Getter
	private final ValidationStep<Profissional> validationStep = new ValidationStep<>();

	public ProfissionalValidator(Profissional profissional) {
		this.profissional = profissional;
	
		this.validationStep.addRule(new NameExistsRule());
		this.validationStep.addRule(new NameEmptyRule());
		this.validationStep.addRule(new CargoExistsRule());
		this.validationStep.addRule(new CargoEmptyRule());

		this.validationStep.validate(this.profissional);
	
	}

	@Override
	public ArrayList<CustomError> getErrors() {
		return this.validationStep.getErrors(); 
	}

	@Override
	public void validate() {
		var errors = getErrors();
		errors.stream().findFirst().ifPresent(error -> {
			throw new CustomException(errors);
		});	
	}

	@Override
	public Validator<Object> withExtraValidator(Validator<Object> next) {
		this.next = next;
		this.next.setErrors(this.errors);
		return this.next;
	}
}