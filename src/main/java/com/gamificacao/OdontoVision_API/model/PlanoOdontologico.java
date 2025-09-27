package com.gamificacao.OdontoVision_API.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "plano_odontologico")
public class PlanoOdontologico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "nome_plano", nullable = false, length = 100)
    private String nomePlano;

    @Size(max = 255)
    private String descricao;

    private BigDecimal preco;

    private LocalDate validade;

    @OneToMany(mappedBy = "plano", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UsuarioPlano> adesoes = new LinkedHashSet<>();

    @OneToMany(mappedBy = "plano", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PlanoCobertura> coberturas = new LinkedHashSet<>();

    @ManyToMany(mappedBy = "planos")
    private Set<Procedimento> procedimentos = new LinkedHashSet<>();

    public PlanoOdontologico() {
    }

    public PlanoOdontologico(String nomePlano) {
        this.nomePlano = nomePlano;
    }

    public Long getId() {
        return id;
    }

    public String getNomePlano() {
        return nomePlano;
    }

    public void setNomePlano(String nomePlano) {
        this.nomePlano = nomePlano;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public LocalDate getValidade() {
        return validade;
    }

    public void setValidade(LocalDate validade) {
        this.validade = validade;
    }

    public Set<UsuarioPlano> getAdesoes() {
        return adesoes;
    }

    public Set<PlanoCobertura> getCoberturas() {
        return coberturas;
    }

    public Set<Procedimento> getProcedimentos() {
        return procedimentos;
    }

    public void adicionarCobertura(String descricaoCobertura) {
        PlanoCobertura cobertura = new PlanoCobertura(this, descricaoCobertura);
        coberturas.add(cobertura);
    }

    public void associarProcedimento(Procedimento procedimento) {
        procedimentos.add(procedimento);
        procedimento.getPlanos().add(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlanoOdontologico that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
