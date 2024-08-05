package com.handle.exception.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.handle.exception.entity.Loja;
import com.handle.exception.interfaces.LojaRepository;
import com.handle.exception.validation.loja.LojaValidator;

import br.com.cassol.cas_ms_exception.exception.errors.CustomException;

@Service
public class LojaService {

	@Autowired
	private LojaRepository lojaRepository;

	/**
	 * Faz validações importante e em seguida executa a regra de negocio.
	 * @param loja entidade para ser cadastrada
	 * @return entidade da loja salva
	 * @throws CustomException caso não passe nas validações
	 */
	public Loja saveLoja(Loja loja) {
		new LojaValidator(loja).validate();
		
		return this.lojaRepository.save(loja);
	}
}