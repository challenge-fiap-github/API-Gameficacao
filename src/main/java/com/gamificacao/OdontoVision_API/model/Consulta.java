package com.gamificacao.OdontoVision_API.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "consulta")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "data_hora", nullable = false)
    private LocalDateTime dataHora;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dentista_id", nullable = false)
    private Dentista dentista;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id", nullable = false)
    private StatusConsulta status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_consulta_id", nullable = false)
    private TipoConsulta tipoConsulta;

    @Column(length = 255)
    private String observacoes;

    @ManyToMany
    @JoinTable(name = "consulta_procedimento",
            joinColumns = @JoinColumn(name = "consulta_id"),
            inverseJoinColumns = @JoinColumn(name = "procedimento_id"))
    private Set<Procedimento> procedimentos = new LinkedHashSet<>();

    @OneToMany(mappedBy = "consulta")
    private Set<Diagnostico> diagnosticos = new LinkedHashSet<>();

    @OneToMany(mappedBy = "consultaValidacao")
    private Set<ChecklistDiario> checklistsValidacoes = new LinkedHashSet<>();

    @OneToMany(mappedBy = "consulta")
    private Set<ValidacaoChecklist> validacoes = new LinkedHashSet<>();

    public Consulta() {
    }

    public Consulta(LocalDateTime dataHora, Usuario usuario, Dentista dentista, StatusConsulta status, TipoConsulta tipoConsulta) {
        this.dataHora = dataHora;
        this.usuario = usuario;
        this.dentista = dentista;
        this.status = status;
        this.tipoConsulta = tipoConsulta;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void definirUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Dentista getDentista() {
        return dentista;
    }

    public void definirDentista(Dentista dentista) {
        this.dentista = dentista;
    }

    public StatusConsulta getStatus() {
        return status;
    }

    public void setStatus(StatusConsulta status) {
        this.status = status;
    }

    public TipoConsulta getTipoConsulta() {
        return tipoConsulta;
    }

    public void setTipoConsulta(TipoConsulta tipoConsulta) {
        this.tipoConsulta = tipoConsulta;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Set<Procedimento> getProcedimentos() {
        return procedimentos;
    }

    public Set<Diagnostico> getDiagnosticos() {
        return diagnosticos;
    }

    public Set<ChecklistDiario> getChecklistsValidacoes() {
        return checklistsValidacoes;
    }

    public Set<ValidacaoChecklist> getValidacoes() {
        return validacoes;
    }

    public void adicionarProcedimento(Procedimento procedimento) {
        procedimentos.add(procedimento);
        procedimento.getConsultas().add(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Consulta consulta)) return false;
        return Objects.equals(id, consulta.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
