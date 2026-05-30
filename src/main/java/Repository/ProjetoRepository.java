package com.amvsolar.repository;

import com.amvsolar.model.Projeto;
import com.amvsolar.model.Projeto.TipoProjeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositório JPA para a entidade Projeto.
 */
@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Long> {

    /** Retorna apenas os projetos ativos (visíveis no site) */
    List<Projeto> findByAtivoTrue();

    /** Filtra projetos ativos por tipo */
    List<Projeto> findByTipoAndAtivoTrue(TipoProjeto tipo);
}