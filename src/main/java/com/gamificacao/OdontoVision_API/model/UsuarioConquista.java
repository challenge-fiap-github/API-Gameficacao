package com.gamificacao.OdontoVision_API.model;

import com.gamificacao.OdontoVision_API.model.id.UsuarioConquistaId;
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
@Table(name = "usuario_conquista")
public class UsuarioConquista {

    @EmbeddedId
    private UsuarioConquistaId id = new UsuarioConquistaId();

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("usuarioId")
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("conquistaId")
    @JoinColumn(name = "conquista_id")
    private Conquista conquista;

    @Column(name = "data_obtencao")
    private LocalDate dataObtencao = LocalDate.now();

    public UsuarioConquista() {
    }

    public UsuarioConquista(Usuario usuario, Conquista conquista) {
        this.usuario = usuario;
        this.conquista = conquista;
        this.id = new UsuarioConquistaId(usuario.getId(), conquista.getId());
    }

    public UsuarioConquistaId getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Conquista getConquista() {
        return conquista;
    }

    public void setConquista(Conquista conquista) {
        this.conquista = conquista;
    }

    public LocalDate getDataObtencao() {
        return dataObtencao;
    }

    public void setDataObtencao(LocalDate dataObtencao) {
        this.dataObtencao = dataObtencao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UsuarioConquista that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
