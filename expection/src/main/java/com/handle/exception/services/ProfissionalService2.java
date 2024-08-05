package com.handle.exception.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.handle.exception.entity.Profissional;
import com.handle.exception.interfaces.LojaRepository;
import com.handle.exception.interfaces.ProfissionalRepository;
import com.handle.exception.validation.loja.LojaValidator;
import com.handle.exception.validation.profissional.ProfissionalValidator;

import br.com.cassol.cas_ms_exception.exception.errors.CustomError;
import br.com.cassol.cas_ms_exception.exception.errors.CustomException;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;

@Service
public class ProfissionalService2 {

	@Autowired
	private ProfissionalRepository profissionalRepository;

	/**
	 * Faz validações importante e em seguida executa a regra de negocio
	 * @param profissional entidade do profissional que sera cadastrado caso valido
	 * @param lojaId loja para cadastrar o profissional
	 * @return entidade do profissional salvo
	 * @throws CustomException caso não passe nas validações
	 */	
	public Profissional saveProfissional(Profissional profissional, Long lojaId) throws CustomException{
		var validator = new ProfissionalValidator(profissional)
			.withExtraValidator(new LojaValidator(lojaId))
			.validate();
		
		return this.profissionalRepository.save(profissional);
	}
}