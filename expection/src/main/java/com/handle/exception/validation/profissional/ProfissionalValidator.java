package com.handle.exception.validation.profissional;

import org.springframework.stereotype.Component;

import com.handle.exception.entity.Profissional;
import com.handle.exception.validation.profissional.regras.CargoEmptyRule;
import com.handle.exception.validation.profissional.regras.CargoExistsRule;
import com.handle.exception.validation.profissional.regras.NameEmptyRule;
import com.handle.exception.validation.profissional.regras.NameExistsRule;

import br.com.cassol.cas_ms_exception.interfaces.Validator;

@Component
public class ProfissionalValidator extends Validator<Profissional> {

	private Profissional profissional;

	public ProfissionalValidator(Profissional profissional) {
		this.profissional = profissional;
		var vStep = this.getValidationStep();

		vStep.addRule(new NameExistsRule());
		vStep.addRule(new NameEmptyRule());
		vStep.addRule(new CargoExistsRule());
		vStep.addRule(new CargoEmptyRule());

		vStep.validate(this.profissional);
	}

}