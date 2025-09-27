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
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "recompensa")
public class Recompensa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 255)
    private String descricao;

    @NotNull
    @Column(name = "pontos_necessarios", nullable = false)
    private Integer pontosNecessarios;

    @Column(name = "quantidade_disponivel")
    private Integer quantidadeDisponivel;

    @Column(name = "data_expiracao")
    private LocalDate dataExpiracao;

    @OneToMany(mappedBy = "recompensa")
    private Set<UsuarioRecompensa> resgates = new LinkedHashSet<>();

    public Recompensa() {
    }

    public Recompensa(String descricao, Integer pontosNecessarios) {
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

    public Integer getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    public void setQuantidadeDisponivel(Integer quantidadeDisponivel) {
        this.quantidadeDisponivel = quantidadeDisponivel;
    }

    public LocalDate getDataExpiracao() {
        return dataExpiracao;
    }

    public void setDataExpiracao(LocalDate dataExpiracao) {
        this.dataExpiracao = dataExpiracao;
    }

    public Set<UsuarioRecompensa> getResgates() {
        return resgates;
    }

    public void registrarResgate(UsuarioRecompensa usuarioRecompensa) {
        resgates.add(usuarioRecompensa);
    }

    public boolean possuiEstoqueDisponivel() {
        return quantidadeDisponivel == null || quantidadeDisponivel > 0;
    }

    public void diminuirEstoque() {
        if (quantidadeDisponivel != null && quantidadeDisponivel > 0) {
            quantidadeDisponivel--;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Recompensa recompensa)) return false;
        return Objects.equals(id, recompensa.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
