package com.handle.exception.domain.loja.validations;

import com.handle.exception.domain.loja.Loja;
import com.handle.exception.domain.loja.validations.rules.LojaEmptyRule;
import com.handle.exception.domain.loja.validations.rules.LojaNameEmptyRule;
import com.handle.exception.domain.loja.validations.rules.LojaNameExistsRule;

import br.com.cassol.cas_ms_exception.interfaces.Validator;

public class LojaValidator extends Validator<Loja> {
	private Loja loja;

	public LojaValidator(Loja loja) {
		this.loja = loja;
		var vStep = this.getValidationStep();

		vStep.addRule(new LojaNameExistsRule());
		vStep.addRule(new LojaEmptyRule());
		vStep.addRule(new LojaNameEmptyRule());
	
		vStep.validate(this.loja);
	}
}