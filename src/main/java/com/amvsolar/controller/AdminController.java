package com.amvsolar.controller;

import com.amvsolar.model.Lead;
import com.amvsolar.model.Lead.StatusLead;
import com.amvsolar.model.Projeto;
import com.amvsolar.model.Depoimento;
import com.amvsolar.service.DepoimentoService;
import com.amvsolar.service.LeadService;
import com.amvsolar.service.ProjetoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Painel administrativo simples para gerenciar
 * leads, projetos e depoimentos.
 *
 * Acesse em: http://localhost:8080/admin
 *
 * ⚠️  Para produção, proteja com Spring Security!
 */
@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final LeadService       leadService;
    private final ProjetoService    projetoService;
    private final DepoimentoService depoimentoService;

    // ===================== DASHBOARD =====================

    @GetMapping
    public String dashboard(Model model) {
        model.addAttribute("totalLeads",  leadService.listarTodos().size());
        model.addAttribute("leadsNovos",  leadService.contarNovos());
        model.addAttribute("leads",       leadService.listarPorStatus(StatusLead.NOVO));
        return "admin/dashboard";
    }

    // ===================== LEADS =====================

    @GetMapping("/leads")
    public String leads(Model model) {
        model.addAttribute("leads", leadService.listarTodos());
        return "admin/leads";
    }

    @PostMapping("/leads/{id}/status")
    public String atualizarStatusLead(@PathVariable Long id,
                                      @RequestParam StatusLead status) {
        leadService.atualizarStatus(id, status);
        return "redirect:/admin/leads";
    }

    // ===================== PROJETOS =====================

    @GetMapping("/projetos")
    public String projetos(Model model) {
        model.addAttribute("projetos", projetoService.listarTodos());
        model.addAttribute("projeto",  new Projeto());
        return "admin/projetos";
    }

    @PostMapping("/projetos/salvar")
    public String salvarProjeto(@ModelAttribute Projeto projeto) {
        projetoService.salvar(projeto);
        return "redirect:/admin/projetos";
    }

    @GetMapping("/projetos/{id}/deletar")
    public String deletarProjeto(@PathVariable Long id) {
        projetoService.deletar(id);
        return "redirect:/admin/projetos";
    }

    // ===================== DEPOIMENTOS =====================

    @GetMapping("/depoimentos")
    public String depoimentos(Model model) {
        model.addAttribute("depoimentos", depoimentoService.listarAtivos());
        model.addAttribute("depoimento",  new Depoimento());
        return "admin/depoimentos";
    }

    @PostMapping("/depoimentos/salvar")
    public String salvarDepoimento(@ModelAttribute Depoimento depoimento) {
        depoimentoService.salvar(depoimento);
        return "redirect:/admin/depoimentos";
    }

    @GetMapping("/depoimentos/{id}/deletar")
    public String deletarDepoimento(@PathVariable Long id) {
        depoimentoService.deletar(id);
        return "redirect:/admin/depoimentos";
    }
}