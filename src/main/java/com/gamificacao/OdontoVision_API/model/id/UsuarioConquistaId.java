package com.gamificacao.OdontoVision_API.model.id;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UsuarioConquistaId implements Serializable {

    @Column(name = "usuario_id")
    private Long usuarioId;

    @Column(name = "conquista_id")
    private Long conquistaId;

    public UsuarioConquistaId() {
    }

    public UsuarioConquistaId(Long usuarioId, Long conquistaId) {
        this.usuarioId = usuarioId;
        this.conquistaId = conquistaId;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public Long getConquistaId() {
        return conquistaId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public void setConquistaId(Long conquistaId) {
        this.conquistaId = conquistaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UsuarioConquistaId that)) return false;
        return Objects.equals(usuarioId, that.usuarioId) && Objects.equals(conquistaId, that.conquistaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuarioId, conquistaId);
    }
}
