/*
package com.gamificacao.OdontoVision_API.service.policy;

import java.time.LocalDate;
import java.util.EnumMap;
import java.util.Map;

*/
/**
 * Singleton de regras de pontuação.
 *
 * - Centraliza valores-base por evento (evita números mágicos na Service).
 * - Permite aplicar multiplicadores e limites por dia/ciclo.
 * - Expõe métodos simples para cálculo de crédito/débito.
 *
 * OBS: Débito por resgate normalmente é o negativo dos pontos necessários
 *      da recompensa; a policy apenas oferece conveniência.
 *//*

public enum PontuacaoPolicy {
    INSTANCE; // Singleton

    */
/** valores-base por evento (pontos positivos) *//*

    private final Map<GamificacaoEvento, Integer> basePorEvento = new EnumMap<>(GamificacaoEvento.class);

    */
/** Limites (por usuário/dia) — pode ser aplicado pela Service se desejar *//*

    private static final int LIMITE_CHECKLIST_DIARIO_POR_DIA = 1;

    PontuacaoPolicy() {
        // configure aqui os seus valores-base
        basePorEvento.put(GamificacaoEvento.CHECKLIST_DIARIO,       10);
        basePorEvento.put(GamificacaoEvento.CONSULTA_REALIZADA,     50);
        basePorEvento.put(GamificacaoEvento.DIAGNOSTICO_REGISTRADO, 20);
        basePorEvento.put(GamificacaoEvento.BONUS_VALIDACAO,        15);
        basePorEvento.put(GamificacaoEvento.AJUSTE_MANUAL,           0); // definido ao lançar
        // RESGATE_RECOMPENSA é débito: tratado como negativo na service
    }

    */
/**
     * Retorna a pontuação de crédito para um determinado evento.
     * @param evento tipo do evento
     * @param quantidade quantas ocorrências (ex.: 2 diagnósticos no mesmo dia)
     * @param data data do registro (para futuros multiplicadores sazonais)
     * @return pontos de crédito (nunca negativo)
     *//*

    public int calcularCredito(GamificacaoEvento evento, int quantidade, LocalDate data) {
        int base = basePorEvento.getOrDefault(evento, 0);
        int pontos = Math.max(0, base) * Math.max(1, quantidade);

        // Exemplo de multiplicador fácil de trocar futuramente
        pontos = aplicarMultiplicadores(evento, pontos, data);

        return pontos;
    }

    */
/**
     * Conveniência para ajuste manual (passa-se o valor desejado).
     * Caso seja negativo, use calcularDebito.
     *//*

    public int calcularCreditoManual(int pontosPositivos) {
        return Math.max(0, pontosPositivos);
    }

    */
/**
     * Débito padrão por resgate (negativo).
     * @param pontosNecessarios pontos exigidos pela recompensa
     *//*

    public int calcularDebitoPorResgate(int pontosNecessarios) {
        return -Math.abs(pontosNecessarios);
    }

    */
/**
     * Regra de negócio: quantas vezes o checklist pode pontuar por dia.
     * Use na Service para bloquear duplo crédito (e.g. existsByUsuarioIdAndData).
     *//*

    public int limiteChecklistDiarioPorDia() {
        return LIMITE_CHECKLIST_DIARIO_POR_DIA;
    }

    */
/** Multiplicadores sazonais ou por tipo de evento (customizável). *//*

    private int aplicarMultiplicadores(GamificacaoEvento evento, int pontos, LocalDate data) {
        if (data == null) return pontos;

        // Exemplo: fim de semana vale 20% a mais para CHECKLIST
        boolean fimDeSemana = data.getDayOfWeek().getValue() >= 6;
        if (evento == GamificacaoEvento.CHECKLIST_DIARIO && fimDeSemana) {
            return Math.round(pontos * 1.2f);
        }
        return pontos;
    }

    */
/** Exponibiliza os valores-base se precisar mostrar na UI. *//*

    public int valorBase(GamificacaoEvento evento) {
        return basePorEvento.getOrDefault(evento, 0);
    }
}
*/
