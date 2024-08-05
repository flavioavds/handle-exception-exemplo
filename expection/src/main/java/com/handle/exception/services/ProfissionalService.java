package com.handle.exception.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.handle.exception.entity.Profissional;
import com.handle.exception.interfaces.LojaRepository;
import com.handle.exception.interfaces.ProfissionalRepository;
import com.handle.exception.validation.profissional.ProfissionalValidator;

import br.com.cassol.cas_ms_exception.exception.errors.CustomError;
import br.com.cassol.cas_ms_exception.exception.errors.CustomException;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProfissionalService {

	@Autowired
	private ProfissionalRepository profissionalRepository;

	@Autowired
	private LojaRepository lojaRepository;

	private ProfissionalValidator profissionalValidator;

	@PostConstruct
	private void initializeValidators() {
		this.profissionalValidator = new ProfissionalValidator(this.profissionalRepository, this.lojaRepository);
	}

	public Profissional saveProfissional(Profissional profissional, Long lojaId) {
		List<CustomError> errors = new ArrayList<>();
		this.profissionalValidator.setLojaId(lojaId);
		this.profissionalValidator.validate(profissional, errors);
		errors.stream().findFirst().ifPresent(error -> {
			throw new CustomException(errors);
		});
		return this.profissionalRepository.save(profissional);
	}
}