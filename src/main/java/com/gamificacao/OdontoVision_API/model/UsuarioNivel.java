package com.gamificacao.OdontoVision_API.model;

import com.gamificacao.OdontoVision_API.model.id.UsuarioNivelId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "usuario_nivel")
public class UsuarioNivel {

    @EmbeddedId
    private UsuarioNivelId id = new UsuarioNivelId();

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("usuarioId")
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("nivelId")
    @JoinColumn(name = "nivel_id")
    private Nivel nivel;

    @Column(name = "pontos_atuais", nullable = false)
    private Integer pontosAtuais;

    @Column(name = "data_ultima_atualizacao")
    private LocalDate dataUltimaAtualizacao = LocalDate.now();

    public UsuarioNivel() {
    }

    public UsuarioNivel(Usuario usuario, Nivel nivel, Integer pontosAtuais) {
        this.usuario = usuario;
        this.nivel = nivel;
        this.pontosAtuais = pontosAtuais;
        this.id = new UsuarioNivelId(usuario.getId(), nivel.getId());
    }

    public UsuarioNivelId getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Nivel getNivel() {
        return nivel;
    }

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }

    public Integer getPontosAtuais() {
        return pontosAtuais;
    }

    public void setPontosAtuais(Integer pontosAtuais) {
        this.pontosAtuais = pontosAtuais;
    }

    public LocalDate getDataUltimaAtualizacao() {
        return dataUltimaAtualizacao;
    }

    public void setDataUltimaAtualizacao(LocalDate dataUltimaAtualizacao) {
        this.dataUltimaAtualizacao = dataUltimaAtualizacao;
    }

    public boolean podeSubirNivel() {
        return nivel != null && pontosAtuais != null && nivel.podeSerAlcancadoPor(pontosAtuais);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UsuarioNivel that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
