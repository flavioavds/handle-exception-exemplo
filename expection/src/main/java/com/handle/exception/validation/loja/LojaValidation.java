package com.handle.exception.validation.loja;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.handle.exception.entity.Loja;
import com.handle.exception.interfaces.LojaRepository;
import com.handle.exception.validation.loja.regras.LojaEmptyRule;
import com.handle.exception.validation.loja.regras.LojaNameEmptyRule;
import com.handle.exception.validation.loja.regras.LojaNameExistsRule;

import br.com.cassol.cas_ms_exception.exception.errors.CustomError;
import br.com.cassol.cas_ms_exception.interfaces.Validator;
import br.com.cassol.cas_ms_exception.validation.ValidationStep;

@Component
public class LojaValidation implements Validator<Loja> {
	private Validator<Loja> next;
	private ValidationStep<Loja> validationStep = new ValidationStep<>();

	public LojaValidation(LojaRepository lojaRepository) {
		this.validationStep.addRule(new LojaNameExistsRule(lojaRepository));
		this.validationStep.addRule(new LojaEmptyRule());
		this.validationStep.addRule(new LojaNameEmptyRule());
	}

	@Override
	public void validate(Loja lojas, List<CustomError> errors) {
		this.validationStep.validate(lojas, errors);
		Optional.ofNullable(this.next).ifPresent(valitador -> valitador.validate(lojas, errors));

	}

	@Override
	public Validator<Loja> linkWith(Validator<Loja> next) {
		this.next = next;
		return next;
	}

}
