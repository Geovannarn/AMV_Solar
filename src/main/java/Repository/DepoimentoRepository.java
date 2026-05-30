package com.amvsolar.repository;

import com.amvsolar.model.Depoimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositório JPA para a entidade Depoimento.
 */
@Repository
public interface DepoimentoRepository extends JpaRepository<Depoimento, Long> {

    /** Retorna apenas os depoimentos ativos */
    List<Depoimento> findByAtivoTrue();

    /** Retorna depoimentos com nota máxima */
    List<Depoimento> findByNotaAndAtivoTrue(int nota);
}