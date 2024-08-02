package com.handle.exception.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_profissional")
public class Profissional {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// @NotBlank
	// @Size(min = 2, max = 40, message = "Nome entre 2 a 40 characters")
	private String name;

	// @NotBlank
	// @Size(min = 2, max = 40, message = "Nome entre 2 a 40 characters")
	private String cargo;

	@ManyToOne
	@JoinColumn(name = "loja_id")
	private Loja loja;

}
