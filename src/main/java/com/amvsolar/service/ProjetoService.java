package com.amvsolar.service;

import com.amvsolar.model.Projeto;
import com.amvsolar.repository.ProjetoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjetoService {

    private final ProjetoRepository projetoRepository;

    /** Retorna os projetos ativos para exibir no site */
    public List<Projeto> listarAtivos() {
        return projetoRepository.findByAtivoTrue();
    }

    /** Retorna todos os projetos (para área admin) */
    public List<Projeto> listarTodos() {
        return projetoRepository.findAll();
    }

    public Projeto salvar(Projeto projeto) {
        return projetoRepository.save(projeto);
    }

    public void deletar(Long id) {
        projetoRepository.deleteById(id);
    }
}