package com.domain.repository;

import com.domain.model.Aposta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApostaRepository extends JpaRepository<Aposta,Long> {
}
