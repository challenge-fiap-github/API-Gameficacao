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
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String nome;

    @NotBlank
    @Email
    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @NotBlank
    @Size(min = 6, max = 100)
    @Column(nullable = false, length = 100)
    private String senha;

    @Past
    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @NotBlank
    @Size(min = 11, max = 11)
    @Column(nullable = false, unique = true, length = 11)
    private String cpf;

    @Column(length = 15)
    private String telefone;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private EnderecoUsuario endereco;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UsuarioPlano> planos = new LinkedHashSet<>();

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Pontuacao> pontuacoes = new LinkedHashSet<>();

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<HistoricoPontuacao> historicoPontuacoes = new LinkedHashSet<>();

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UsuarioRecompensa> recompensasResgatadas = new LinkedHashSet<>();

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UsuarioNivel> niveis = new LinkedHashSet<>();

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UsuarioConquista> conquistas = new LinkedHashSet<>();

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ChecklistDiario> checklists = new LinkedHashSet<>();

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ValidacaoChecklist> validacoesChecklist = new LinkedHashSet<>();

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Notificacao> notificacoes = new LinkedHashSet<>();

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Consulta> consultas = new LinkedHashSet<>();

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Sinistro> sinistros = new LinkedHashSet<>();

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<HistoricoTratamento> historicosTratamento = new LinkedHashSet<>();

    public Usuario() {
    }

    public Usuario(String nome, String email, String senha, LocalDate dataNascimento, String cpf, String telefone) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.telefone = telefone;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public EnderecoUsuario getEndereco() {
        return endereco;
    }

    public void definirEndereco(EnderecoUsuario endereco) {
        if (endereco == null) {
            if (this.endereco != null) {
                this.endereco.definirUsuario(null);
            }
            this.endereco = null;
            return;
        }
        endereco.definirUsuario(this);
        this.endereco = endereco;
    }

    public Set<UsuarioPlano> getPlanos() {
        return planos;
    }

    public Set<Pontuacao> getPontuacoes() {
        return pontuacoes;
    }

    public Set<HistoricoPontuacao> getHistoricoPontuacoes() {
        return historicoPontuacoes;
    }

    public Set<UsuarioRecompensa> getRecompensasResgatadas() {
        return recompensasResgatadas;
    }

    public Set<UsuarioNivel> getNiveis() {
        return niveis;
    }

    public Set<UsuarioConquista> getConquistas() {
        return conquistas;
    }

    public Set<ChecklistDiario> getChecklists() {
        return checklists;
    }

    public Set<ValidacaoChecklist> getValidacoesChecklist() {
        return validacoesChecklist;
    }

    public Set<Notificacao> getNotificacoes() {
        return notificacoes;
    }

    public Set<Consulta> getConsultas() {
        return consultas;
    }

    public Set<Sinistro> getSinistros() {
        return sinistros;
    }

    public Set<HistoricoTratamento> getHistoricosTratamento() {
        return historicosTratamento;
    }

    public void adicionarPlano(UsuarioPlano usuarioPlano) {
        usuarioPlano.definirUsuario(this);
        planos.add(usuarioPlano);
    }

    public void adicionarPontuacao(Pontuacao pontuacao) {
        pontuacao.definirUsuario(this);
        pontuacoes.add(pontuacao);
    }

    public void adicionarChecklist(ChecklistDiario checklistDiario) {
        checklistDiario.definirUsuario(this);
        checklists.add(checklistDiario);
    }

    public void adicionarNotificacao(Notificacao notificacao) {
        notificacao.definirUsuario(this);
        notificacoes.add(notificacao);
    }

    public void agendarConsulta(Consulta consulta) {
        consulta.definirUsuario(this);
        consultas.add(consulta);
    }

    public void registrarSinistro(Sinistro sinistro) {
        sinistro.definirPaciente(this);
        sinistros.add(sinistro);
    }

    public void registrarNivel(UsuarioNivel usuarioNivel) {
        usuarioNivel.setUsuario(this);
        niveis.add(usuarioNivel);
    }

    public void adicionarConquista(UsuarioConquista usuarioConquista) {
        usuarioConquista.setUsuario(this);
        conquistas.add(usuarioConquista);
    }

    public void registrarResgate(Recompensa recompensa, UsuarioRecompensa usuarioRecompensa) {
        usuarioRecompensa.setUsuario(this);
        usuarioRecompensa.setRecompensa(recompensa);
        recompensasResgatadas.add(usuarioRecompensa);
        recompensa.registrarResgate(usuarioRecompensa);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario usuario)) return false;
        return Objects.equals(id, usuario.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
