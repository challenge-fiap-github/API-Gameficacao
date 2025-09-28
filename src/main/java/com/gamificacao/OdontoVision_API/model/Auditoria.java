package com.gamificacao.OdontoVision_API.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "auditoria")
public class Auditoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // casa com INT do MySQL

    @Column(name = "tabela_afetada", nullable = false, length = 50)
    private String tabelaAfetada;

    @Column(name = "tipo_operacao", nullable = false, length = 10)
    private String tipoOperacao;

    @Column(name = "usuario_id")
    private Integer usuarioId;

    @Column(name = "data_operacao")
    private LocalDateTime dataOperacao;

    // Remova @Lob e force TEXT
    @Column(name = "valores_antigos", columnDefinition = "TEXT")
    private String valoresAntigos;

    @Column(name = "valores_novos", columnDefinition = "TEXT")
    private String valoresNovos;

    public Integer getId() { return id; }
    public String getTabelaAfetada() { return tabelaAfetada; }
    public void setTabelaAfetada(String tabelaAfetada) { this.tabelaAfetada = tabelaAfetada; }
    public String getTipoOperacao() { return tipoOperacao; }
    public void setTipoOperacao(String tipoOperacao) { this.tipoOperacao = tipoOperacao; }
    public Integer getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Integer usuarioId) { this.usuarioId = usuarioId; }
    public LocalDateTime getDataOperacao() { return dataOperacao; }
    public void setDataOperacao(LocalDateTime dataOperacao) { this.dataOperacao = dataOperacao; }
    public String getValoresAntigos() { return valoresAntigos; }
    public void setValoresAntigos(String valoresAntigos) { this.valoresAntigos = valoresAntigos; }
    public String getValoresNovos() { return valoresNovos; }
    public void setValoresNovos(String valoresNovos) { this.valoresNovos = valoresNovos; }
}
