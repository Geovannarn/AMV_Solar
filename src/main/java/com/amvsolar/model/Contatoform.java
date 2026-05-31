package com.amvsolar.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

/**
 * DTO do formulário de simulação (não é uma entidade JPA).
 * Recebe os dados do formulário Thymeleaf e os valida
 * antes de redirecionar para o WhatsApp.
 */
@Data
public class Contatoform {

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @NotBlank(message = "WhatsApp é obrigatório")
    @Pattern(
            regexp = "\\(?\\d{2}\\)?[\\s-]?9?\\d{4}-?\\d{4}",
            message = "Informe um telefone válido"
    )
    private String telefone;

    private String cidade;

    /** Faixa de valor da conta de luz selecionada */
    private String contaLuz;

    /** Residencial, Comercial, Rural, Industrial */
    private String tipoInstalacao;

    private String mensagem;

    /** Converte este DTO em uma entidade Lead para salvar no banco */
    public Lead toLead() {
        Lead lead = new Lead();
        lead.setNome(this.nome);
        lead.setTelefone(this.telefone);
        lead.setCidade(this.cidade);
        lead.setContaLuz(this.contaLuz);
        lead.setTipoInstalacao(this.tipoInstalacao);
        lead.setMensagem(this.mensagem);
        return lead;
    }
}