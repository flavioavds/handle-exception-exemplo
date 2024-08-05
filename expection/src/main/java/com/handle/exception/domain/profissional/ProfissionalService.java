package com.handle.exception.domain.profissional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.handle.exception.domain.loja.Loja;
import com.handle.exception.domain.loja.validations.LojaValidator;
import com.handle.exception.domain.profissional.validations.ProfissionalValidator;

import br.com.cassol.cas_ms_exception.exception.errors.CustomException;

@Service
public class ProfissionalService {

	@Autowired
	private ProfissionalRepository profissionalRepository;

	/**
	 * Faz validações importante e em seguida executa a regra de negocio
	 * @param profissional entidade do profissional que sera cadastrado caso valido
	 * @param lojaId loja para cadastrar o profissional
	 * @return entidade do profissional salvo
	 * @throws CustomException caso não passe nas validações
	 */	
	public Profissional saveProfissional(Profissional profissional, Loja loja) throws CustomException{
		new ProfissionalValidator(profissional)
			.withExtraValidator(new LojaValidator(loja))
			.validate();
		
		return this.profissionalRepository.save(profissional);
	}
}