package com.handle.exception.validation.loja;

import org.springframework.stereotype.Component;

import com.handle.exception.entity.Loja;
import com.handle.exception.validation.loja.regras.LojaEmptyRule;
import com.handle.exception.validation.loja.regras.LojaNameEmptyRule;
import com.handle.exception.validation.loja.regras.LojaNameExistsRule;

import br.com.cassol.cas_ms_exception.interfaces.Validator;

@Component
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