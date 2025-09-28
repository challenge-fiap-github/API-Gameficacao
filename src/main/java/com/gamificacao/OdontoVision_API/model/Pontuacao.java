package com.gamificacao.OdontoVision_API.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "pontuacao")
public class Pontuacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(nullable = false)
    private Integer pontos;

    @Column(name = "data_registro", nullable = false)
    private LocalDate dataRegistro;

    @Column(name = "ciclo_inicial")
    private LocalDate cicloInicial;

    @Column(name = "ciclo_final")
    private LocalDate cicloFinal;

    public Pontuacao() {
    }

    // --- Builder manual simples (opcional) ---
    public static Builder builder() {
        return new Builder();
    }
    public static class Builder {
        private final Pontuacao p = new Pontuacao();
        public Builder usuario(Usuario u) { p.usuario = u; return this; }
        public Builder pontos(Integer v) { p.pontos = v; return this; }
        public Builder dataRegistro(LocalDate d) { p.dataRegistro = d; return this; }
        public Builder cicloInicial(LocalDate d) { p.cicloInicial = d; return this; }
        public Builder cicloFinal(LocalDate d) { p.cicloFinal = d; return this; }
        public Pontuacao build() { return p; }
    }
    // -----------------------------------------

    public Long getId() { return id; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public Integer getPontos() { return pontos; }
    public void setPontos(Integer pontos) { this.pontos = pontos; }

    public LocalDate getDataRegistro() { return dataRegistro; }
    public void setDataRegistro(LocalDate dataRegistro) { this.dataRegistro = dataRegistro; }

    public LocalDate getCicloInicial() { return cicloInicial; }
    public void setCicloInicial(LocalDate cicloInicial) { this.cicloInicial = cicloInicial; }

    public LocalDate getCicloFinal() { return cicloFinal; }
    public void setCicloFinal(LocalDate cicloFinal) { this.cicloFinal = cicloFinal; }

    public void definirUsuario(Usuario u) {
        this.usuario = u;
        if (u != null) u.getPontuacoes().add(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pontuacao that)) return false;
        return Objects.equals(id, that.id);
    }
    @Override
    public int hashCode() { return Objects.hash(id); }
}
