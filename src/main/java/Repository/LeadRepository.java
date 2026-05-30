package com.amvsolar.repository;

import com.amvsolar.model.Lead;
import com.amvsolar.model.Lead.StatusLead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositório JPA para a entidade Lead.
 * Spring Data gera automaticamente as implementações.
 */
@Repository
public interface LeadRepository extends JpaRepository<Lead, Long> {

    /** Busca todos os leads de uma cidade específica */
    List<Lead> findByCidade(String cidade);

    /** Busca leads por status (NOVO, EM_CONTATO, etc.) */
    List<Lead> findByStatus(StatusLead status);

    /** Busca leads por tipo de instalação */
    List<Lead> findByTipoInstalacao(String tipoInstalacao);

    /** Conta quantos leads novos existem */
    long countByStatus(StatusLead status);
}