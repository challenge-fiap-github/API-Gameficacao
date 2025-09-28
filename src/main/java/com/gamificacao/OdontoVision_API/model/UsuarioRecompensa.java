package com.gamificacao.OdontoVision_API.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "usuario_recompensa")
public class UsuarioRecompensa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // FK: usuario_id
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    // FK: recompensa_id
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "recompensa_id", nullable = false)
    private Recompensa recompensa;

    @Column(name = "data_resgate")
    private LocalDate dataResgate;

    public UsuarioRecompensa() {}

    public Long getId() { return id; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public Recompensa getRecompensa() { return recompensa; }
    public void setRecompensa(Recompensa recompensa) { this.recompensa = recompensa; }

    public LocalDate getDataResgate() { return dataResgate; }
    public void setDataResgate(LocalDate dataResgate) { this.dataResgate = dataResgate; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UsuarioRecompensa that)) return false;
        return Objects.equals(id, that.id);
    }
    @Override
    public int hashCode() { return Objects.hash(id); }
}
