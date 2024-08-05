package com.handle.exception.domain.profissional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProfissionalRepository extends JpaRepository<Profissional, Long> {

	boolean existsByName(String nome);
	boolean existsByCargo(String cargo);

}
