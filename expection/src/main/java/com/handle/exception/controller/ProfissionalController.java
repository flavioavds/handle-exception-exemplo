package com.handle.exception.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.handle.exception.entity.Profissional;
import com.handle.exception.services.ProfissionalService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/profissionais")
@Validated
public class ProfissionalController {

	private final ProfissionalService profissionalService;

	public ProfissionalController(ProfissionalService profissionalService) {
		this.profissionalService = profissionalService;
	}

	@PostMapping("/{lojaId}")
	public ResponseEntity<Profissional> createProfissional(@Valid @RequestBody Profissional profissional,
			@PathVariable Long lojaId) {
		Profissional savedProfissional = this.profissionalService.saveProfissional(profissional, lojaId);
		return new ResponseEntity<>(savedProfissional, HttpStatus.CREATED);
	}
}