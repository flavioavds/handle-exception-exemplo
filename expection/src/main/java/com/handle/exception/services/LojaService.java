package com.handle.exception.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.handle.exception.entity.Loja;
import com.handle.exception.interfaces.LojaRepository;
import com.handle.exception.validation.loja.LojaNameValidator;
import com.handle.exception.validation.loja.LojaNotNullValidator;

import br.com.cassol.cas_ms_exception.exception.errors.CustomError;
import br.com.cassol.cas_ms_exception.exception.errors.CustomException;
import br.com.cassol.cas_ms_exception.interfaces.Validator;

@Service
public class LojaService {

	private final LojaRepository lojaRepository;
	private List<Validator<Loja>> validators;

	public LojaService(LojaRepository lojaRepository) {
		this.lojaRepository = lojaRepository;
		this.initializeValidators();
	}

	private void initializeValidators() {
		this.validators = new ArrayList<>();

		Validator<Loja> lojaNotNullValidator = new LojaNotNullValidator();
		Validator<Loja> lojaNameValidator = new LojaNameValidator();
		lojaNotNullValidator.linkWith(lojaNameValidator);

		this.validators.add(lojaNotNullValidator);
	}

	public Loja saveLoja(Loja loja) {
		List<CustomError> errors = new ArrayList<>();
		for (Validator<Loja> validator : this.validators) {
			validator.validate(loja, errors);
		}

		if (!errors.isEmpty()) {
			throw new CustomException(errors);
		}

		return this.lojaRepository.save(loja);
	}
}