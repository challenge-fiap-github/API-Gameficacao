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
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "sinistro")
public class Sinistro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id")
    private Usuario paciente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "procedimento_id")
    private Procedimento procedimento;

    @Column(name = "data_sinistro")
    private LocalDate dataSinistro;

    @Column(name = "risco_fraude", length = 1)
    private SimNao riscoFraude;

    @Column(name = "descricao_risco", length = 255)
    private String descricaoRisco;

    public Sinistro() {
    }

    public Sinistro(Usuario paciente, Procedimento procedimento, LocalDate dataSinistro) {
        this.paciente = paciente;
        this.procedimento = procedimento;
        this.dataSinistro = dataSinistro;
    }

    public Long getId() {
        return id;
    }

    public Usuario getPaciente() {
        return paciente;
    }

    public void definirPaciente(Usuario paciente) {
        this.paciente = paciente;
    }

    public Procedimento getProcedimento() {
        return procedimento;
    }

    public void setProcedimento(Procedimento procedimento) {
        this.procedimento = procedimento;
    }

    public LocalDate getDataSinistro() {
        return dataSinistro;
    }

    public void setDataSinistro(LocalDate dataSinistro) {
        this.dataSinistro = dataSinistro;
    }

    public SimNao getRiscoFraude() {
        return riscoFraude;
    }

    public void setRiscoFraude(SimNao riscoFraude) {
        this.riscoFraude = riscoFraude;
    }

    public String getDescricaoRisco() {
        return descricaoRisco;
    }

    public void setDescricaoRisco(String descricaoRisco) {
        this.descricaoRisco = descricaoRisco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sinistro sinistro)) return false;
        return Objects.equals(id, sinistro.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
