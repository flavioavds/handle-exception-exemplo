package com.handle.exception.validation.loja;

import java.util.List;

import com.handle.exception.entity.Loja;
import com.handle.exception.exception.constants.ErrorMessages;

import br.com.cassol.cas_ms_exception.exception.errors.CustomError;
import br.com.cassol.cas_ms_exception.interfaces.Validator;

public class LojaNameValidator implements Validator<Loja> {
	private Validator<Loja> next;

	@Override
	public void validate(Loja loja, List<CustomError> errors) {
		if (loja.getName() == null || loja.getName().isEmpty()) {
			errors.add(new CustomError("loja", ErrorMessages.LOJA_NAME_NULL_EMPTY));
		}
		if (this.next != null) {
			this.next.validate(loja, errors);
		}
	}

	@Override
	public Validator<Loja> linkWith(Validator<Loja> next) {
		this.next = next;
		return next;
	}
}