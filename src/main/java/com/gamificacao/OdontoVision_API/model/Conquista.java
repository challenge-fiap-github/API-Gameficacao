package com.gamificacao.OdontoVision_API.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "conquista")
public class Conquista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String nome;

    @NotBlank
    @Column(nullable = false, length = 255)
    private String descricao;

    @Column(name = "pontos_bonus")
    private Integer pontosBonus;

    @Column(name = "data_expiracao")
    private LocalDate dataExpiracao;

    @OneToMany(mappedBy = "conquista")
    private Set<UsuarioConquista> usuarios = new LinkedHashSet<>();

    public Conquista() {
    }

    public Conquista(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getPontosBonus() {
        return pontosBonus;
    }

    public void setPontosBonus(Integer pontosBonus) {
        this.pontosBonus = pontosBonus;
    }

    public LocalDate getDataExpiracao() {
        return dataExpiracao;
    }

    public void setDataExpiracao(LocalDate dataExpiracao) {
        this.dataExpiracao = dataExpiracao;
    }

    public Set<UsuarioConquista> getUsuarios() {
        return usuarios;
    }

    public boolean estaValida(LocalDate dataReferencia) {
        return dataExpiracao == null || !dataExpiracao.isBefore(dataReferencia);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Conquista conquista)) return false;
        return Objects.equals(id, conquista.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
