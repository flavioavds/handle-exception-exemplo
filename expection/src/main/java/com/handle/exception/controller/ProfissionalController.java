package com.handle.exception.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.handle.exception.domain.loja.Loja;
import com.handle.exception.domain.profissional.Profissional;
import com.handle.exception.domain.profissional.ProfissionalService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/profissionais")
@Validated
public class ProfissionalController {
	@Autowired
	private ProfissionalService profissionalService;

	@PostMapping
	public ResponseEntity<Profissional> createProfissional(@Valid @RequestBody Profissional profissional,
			Loja loja) {
		Profissional savedProfissional = this.profissionalService.saveProfissional(profissional, loja);
		return new ResponseEntity<>(savedProfissional, HttpStatus.CREATED);
	}
}