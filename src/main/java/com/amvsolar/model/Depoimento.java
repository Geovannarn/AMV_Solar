package com.amvsolar.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * Depoimento de cliente exibido na seção "Clientes" do site.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "depoimentos")
public class Depoimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    private String cidade;

    /** Texto do depoimento */
    @Column(nullable = false, columnDefinition = "TEXT")
    private String texto;

    /** Nota de 1 a 5 */
    private int nota = 5;

    /** Ex: "-91%" — percentual de economia */
    private String economia;

    /** Iniciais do avatar, ex: "MR" */
    private String iniciais;

    private boolean ativo = true;
}