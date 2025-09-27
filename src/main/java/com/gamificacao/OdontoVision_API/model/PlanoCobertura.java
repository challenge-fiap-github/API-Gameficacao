package com.gamificacao.OdontoVision_API.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "plano_cobertura")
public class PlanoCobertura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plano_id", nullable = false)
    private PlanoOdontologico plano;

    @NotBlank
    @Size(max = 255)
    @Column(nullable = false)
    private String descricao;

    public PlanoCobertura() {
    }

    public PlanoCobertura(PlanoOdontologico plano, String descricao) {
        this.plano = plano;
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public PlanoOdontologico getPlano() {
        return plano;
    }

    public void setPlano(PlanoOdontologico plano) {
        this.plano = plano;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlanoCobertura that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
