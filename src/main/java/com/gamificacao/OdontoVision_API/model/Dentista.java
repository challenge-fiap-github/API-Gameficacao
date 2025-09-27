package com.gamificacao.OdontoVision_API.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "dentista")
public class Dentista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String nome;

    @NotBlank
    @Column(nullable = false, unique = true, length = 20)
    private String cro;

    @Size(max = 100)
    private String especialidade;

    @Size(max = 15)
    private String telefone;

    @NotBlank
    @Email
    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @OneToOne(mappedBy = "dentista", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private EnderecoClinica endereco;

    @OneToMany(mappedBy = "dentista", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Consulta> consultas = new LinkedHashSet<>();

    @OneToMany(mappedBy = "dentista", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<HistoricoTratamento> historicosTratamento = new LinkedHashSet<>();

    public Dentista() {
    }

    public Dentista(String nome, String cro, String especialidade, String telefone, String email) {
        this.nome = nome;
        this.cro = cro;
        this.especialidade = especialidade;
        this.telefone = telefone;
        this.email = email;
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

    public String getCro() {
        return cro;
    }

    public void setCro(String cro) {
        this.cro = cro;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public EnderecoClinica getEndereco() {
        return endereco;
    }

    public void definirEndereco(EnderecoClinica endereco) {
        if (endereco == null) {
            if (this.endereco != null) {
                this.endereco.definirDentista(null);
            }
            this.endereco = null;
            return;
        }
        endereco.definirDentista(this);
        this.endereco = endereco;
    }

    public Set<Consulta> getConsultas() {
        return consultas;
    }

    public Set<HistoricoTratamento> getHistoricosTratamento() {
        return historicosTratamento;
    }

    public void agendarConsulta(Consulta consulta) {
        consulta.definirDentista(this);
        consultas.add(consulta);
    }

    public void registrarTratamento(HistoricoTratamento historicoTratamento) {
        historicoTratamento.setDentista(this);
        historicosTratamento.add(historicoTratamento);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dentista dentista)) return false;
        return Objects.equals(id, dentista.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
