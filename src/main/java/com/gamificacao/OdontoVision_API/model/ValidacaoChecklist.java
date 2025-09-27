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
@Table(name = "validacao_checklist")
public class ValidacaoChecklist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "consulta_id", nullable = false)
    private Consulta consulta;

    @Column(name = "data_validacao")
    private LocalDate dataValidacao = LocalDate.now();

    @Column(name = "status_validacao", length = 1)
    private SimNao statusValidacao;

    @Column(name = "pontos_bonus")
    private Integer pontosBonus;

    public ValidacaoChecklist() {
    }

    public ValidacaoChecklist(Usuario usuario, Consulta consulta, SimNao statusValidacao) {
        this.usuario = usuario;
        this.consulta = consulta;
        this.statusValidacao = statusValidacao;
    }

    public Long getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }

    public LocalDate getDataValidacao() {
        return dataValidacao;
    }

    public void setDataValidacao(LocalDate dataValidacao) {
        this.dataValidacao = dataValidacao;
    }

    public SimNao getStatusValidacao() {
        return statusValidacao;
    }

    public void setStatusValidacao(SimNao statusValidacao) {
        this.statusValidacao = statusValidacao;
    }

    public Integer getPontosBonus() {
        return pontosBonus;
    }

    public void setPontosBonus(Integer pontosBonus) {
        this.pontosBonus = pontosBonus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ValidacaoChecklist that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
