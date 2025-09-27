package com.gamificacao.OdontoVision_API.model.id;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UsuarioNivelId implements Serializable {

    @Column(name = "usuario_id")
    private Long usuarioId;

    @Column(name = "nivel_id")
    private Long nivelId;

    public UsuarioNivelId() {
    }

    public UsuarioNivelId(Long usuarioId, Long nivelId) {
        this.usuarioId = usuarioId;
        this.nivelId = nivelId;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public Long getNivelId() {
        return nivelId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public void setNivelId(Long nivelId) {
        this.nivelId = nivelId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UsuarioNivelId that)) return false;
        return Objects.equals(usuarioId, that.usuarioId) && Objects.equals(nivelId, that.nivelId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuarioId, nivelId);
    }
}
