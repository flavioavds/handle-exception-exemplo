package com.handle.exception.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.handle.exception.entity.Profissional;

@Repository
public interface ProfissionalRepository extends JpaRepository<Profissional, Long> {

	boolean existsByName(String nome);
	boolean existsByCargo(String cargo);

}
