package com.amvsolar.service;

import com.amvsolar.model.Depoimento;
import com.amvsolar.repository.DepoimentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepoimentoService {

    private final DepoimentoRepository depoimentoRepository;

    /** Retorna depoimentos ativos para o site */
    public List<Depoimento> listarAtivos() {
        return depoimentoRepository.findByAtivoTrue();
    }

    public Depoimento salvar(Depoimento depoimento) {
        return depoimentoRepository.save(depoimento);
    }

    public void deletar(Long id) {
        depoimentoRepository.deleteById(id);
    }
}