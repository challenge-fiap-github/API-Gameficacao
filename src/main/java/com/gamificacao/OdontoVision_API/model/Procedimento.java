package com.gamificacao.OdontoVision_API.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "procedimento")
public class Procedimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "nome_procedimento", nullable = false, length = 100)
    private String nomeProcedimento;

    @Size(max = 255)
    private String descricao;

    private BigDecimal custo;

    @ManyToMany(mappedBy = "procedimentos")
    private Set<Consulta> consultas = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "plano_procedimento",
            joinColumns = @JoinColumn(name = "procedimento_id"),
            inverseJoinColumns = @JoinColumn(name = "plano_id"))
    private Set<PlanoOdontologico> planos = new LinkedHashSet<>();

    public Procedimento() {
    }

    public Procedimento(String nomeProcedimento) {
        this.nomeProcedimento = nomeProcedimento;
    }

    public Long getId() {
        return id;
    }

    public String getNomeProcedimento() {
        return nomeProcedimento;
    }

    public void setNomeProcedimento(String nomeProcedimento) {
        this.nomeProcedimento = nomeProcedimento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getCusto() {
        return custo;
    }

    public void setCusto(BigDecimal custo) {
        this.custo = custo;
    }

    public Set<Consulta> getConsultas() {
        return consultas;
    }

    public Set<PlanoOdontologico> getPlanos() {
        return planos;
    }

    public void associarPlano(PlanoOdontologico plano) {
        planos.add(plano);
        plano.getProcedimentos().add(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Procedimento that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
