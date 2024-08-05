package com.handle.exception.domain.profissional.validations;

import com.handle.exception.domain.profissional.Profissional;
import com.handle.exception.domain.profissional.validations.rules.CargoEmptyRule;
import com.handle.exception.domain.profissional.validations.rules.CargoExistsRule;
import com.handle.exception.domain.profissional.validations.rules.NameEmptyRule;
import com.handle.exception.domain.profissional.validations.rules.NameExistsRule;

import br.com.cassol.cas_ms_exception.interfaces.Validator;


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