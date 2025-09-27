package com.gamificacao.OdontoVision_API.model;

import com.gamificacao.OdontoVision_API.model.enums.SimNao;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "checklist_diario")
public class ChecklistDiario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @NotNull
    @Column(nullable = false)
    private LocalDate data;

    @Column(length = 1)
    private SimNao escovacao;

    @Column(name = "fio_dental", length = 1)
    private SimNao fioDental;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "consulta_validacao_id")
    private Consulta consultaValidacao;

    public ChecklistDiario() {
    }

    public ChecklistDiario(Usuario usuario, LocalDate data) {
        this.usuario = usuario;
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void definirUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public SimNao getEscovacao() {
        return escovacao;
    }

    public void setEscovacao(SimNao escovacao) {
        this.escovacao = escovacao;
    }

    public SimNao getFioDental() {
        return fioDental;
    }

    public void setFioDental(SimNao fioDental) {
        this.fioDental = fioDental;
    }

    public Consulta getConsultaValidacao() {
        return consultaValidacao;
    }

    public void setConsultaValidacao(Consulta consultaValidacao) {
        this.consultaValidacao = consultaValidacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChecklistDiario that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
