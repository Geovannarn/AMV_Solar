package com.amvsolar.service;

import com.amvsolar.model.Lead;
import com.amvsolar.model.Lead.StatusLead;
import com.amvsolar.repository.LeadRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class LeadService {

    private final LeadRepository leadRepository;

    /** Salva um novo lead no banco */
    public Lead salvar(Lead lead) {
        log.info("Novo lead salvo: {} — {}", lead.getNome(), lead.getTelefone());
        return leadRepository.save(lead);
    }

    /** Lista todos os leads */
    public List<Lead> listarTodos() {
        return leadRepository.findAll();
    }

    /** Lista leads por status */
    public List<Lead> listarPorStatus(StatusLead status) {
        return leadRepository.findByStatus(status);
    }

    /** Atualiza o status de um lead */
    public Lead atualizarStatus(Long id, StatusLead novoStatus) {
        Lead lead = leadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lead não encontrado: " + id));
        lead.setStatus(novoStatus);
        return leadRepository.save(lead);
    }

    /** Conta leads novos */
    public long contarNovos() {
        return leadRepository.countByStatus(StatusLead.NOVO);
    }
}