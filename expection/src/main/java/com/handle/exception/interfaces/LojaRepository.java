package com.handle.exception.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.handle.exception.entity.Loja;

@Repository
public interface LojaRepository extends JpaRepository<Loja, Long> {
}
