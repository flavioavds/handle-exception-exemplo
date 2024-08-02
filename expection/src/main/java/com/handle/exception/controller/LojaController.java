package com.handle.exception.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.handle.exception.entity.Loja;
import com.handle.exception.services.LojaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/lojas")
@Validated
public class LojaController {

	private final LojaService lojaService;

	public LojaController(LojaService lojaService) {
		this.lojaService = lojaService;
	}

	@PostMapping
	public ResponseEntity<Loja> createLoja(@Valid @RequestBody Loja loja) {
		Loja savedLoja = this.lojaService.saveLoja(loja);
		return new ResponseEntity<>(savedLoja, HttpStatus.CREATED);
	}
}