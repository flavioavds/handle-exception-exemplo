package com.handle.exception.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.handle.exception.entity.Loja;
import com.handle.exception.interfaces.LojaRepository;
import com.handle.exception.validation.loja.LojaValidation;

import br.com.cassol.cas_ms_exception.exception.errors.CustomError;
import br.com.cassol.cas_ms_exception.exception.errors.CustomException;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LojaService {

	@Autowired
	private LojaRepository lojaRepository;

	private LojaValidation lojaValidation;

	@PostConstruct
	private void initializeValidators() {
		this.lojaValidation = new LojaValidation(this.lojaRepository);
	}

	public Loja saveLoja(Loja loja) {
		List<CustomError> errors = new ArrayList<>();

		this.lojaValidation.validate(loja, errors);

		errors.stream().findFirst().ifPresent(error -> {
			throw new CustomException(errors);
		});

		return this.lojaRepository.save(loja);
	}
}