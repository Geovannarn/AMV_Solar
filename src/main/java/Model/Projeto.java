package com.amvsolar.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * Representa um projeto solar realizado pela AMV Solar.
 * Exibido na seção "Portfólio" do site.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "projetos")
public class Projeto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    private String cidade;

    @Enumerated(EnumType.STRING)
    private TipoProjeto tipo;

    /** Potência do sistema em kWp */
    @Column(name = "potencia_kwp")
    private Double potenciaKwp;

    /** Economia mensal estimada em R$ */
    @Column(name = "economia_mensal")
    private Double economiaMensal;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    /** Nome do arquivo de imagem em /static/images/ */
    @Column(name = "imagem_url")
    private String imagemUrl;

    private boolean ativo = true;

    public enum TipoProjeto {
        RESIDENCIAL,
        COMERCIAL,
        RURAL,
        INDUSTRIAL
    }
}