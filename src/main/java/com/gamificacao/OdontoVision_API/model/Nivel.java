package com.gamificacao.OdontoVision_API.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "nivel")
public class Nivel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String descricao;

    @NotNull
    @Column(name = "pontos_necessarios", nullable = false)
    private Integer pontosNecessarios;

    @OneToMany(mappedBy = "nivel")
    private Set<UsuarioNivel> usuarios = new LinkedHashSet<>();

    public Nivel() {
    }

    public Nivel(String descricao, Integer pontosNecessarios) {
        this.descricao = descricao;
        this.pontosNecessarios = pontosNecessarios;
    }

    public Long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getPontosNecessarios() {
        return pontosNecessarios;
    }

    public void setPontosNecessarios(Integer pontosNecessarios) {
        this.pontosNecessarios = pontosNecessarios;
    }

    public Set<UsuarioNivel> getUsuarios() {
        return usuarios;
    }

    public boolean podeSerAlcancadoPor(int pontosAtuais) {
        return pontosAtuais >= pontosNecessarios;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Nivel nivel)) return false;
        return Objects.equals(id, nivel.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
