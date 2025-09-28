package com.gamificacao.OdontoVision_API.service;

import com.gamificacao.OdontoVision_API.dto.gamificacao.*;
import com.gamificacao.OdontoVision_API.exception.BusinessException;
import com.gamificacao.OdontoVision_API.exception.NotFoundException;
import com.gamificacao.OdontoVision_API.mapper.PontuacaoMapper;
import com.gamificacao.OdontoVision_API.model.Pontuacao;
import com.gamificacao.OdontoVision_API.model.Recompensa;
import com.gamificacao.OdontoVision_API.model.Usuario;
import com.gamificacao.OdontoVision_API.model.UsuarioRecompensa;
import com.gamificacao.OdontoVision_API.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class GamificacaoService {

    private final UsuarioRepository usuarioRepository;
    private final PontuacaoRepository pontuacaoRepository;
    private final UsuarioRecompensaRepository usuarioRecompensaRepository;
    private final RecompensaRepository recompensaRepository;
    private final PontuacaoMapper pontuacaoMapper;

    /** Credita/debita pontos diretamente (para eventos do app). */
    @Transactional
    public PontuacaoDTO lancar(CreditoPontosDTO dto) { // <- sem acento
        Usuario usuario = usuarioRepository.findById(dto.usuarioId())
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));

        Pontuacao p = pontuacaoMapper.toEntity(dto);
        p.setUsuario(usuario);
        if (p.getDataRegistro() == null) {
            p.setDataRegistro(LocalDate.now());
        }

        Pontuacao salvo = pontuacaoRepository.save(p);
        return pontuacaoMapper.toDTO(salvo);
    }

    /** Retorna o saldo atual (soma dos pontos). */
    public SaldoDTO saldo(Long usuarioId) {
        validarUsuarioExiste(usuarioId);
        Integer total = pontuacaoRepository.sumPontosByUsuario(usuarioId);
        return new SaldoDTO(usuarioId, total == null ? 0 : total);
    }

    /** Extrato de movimentações de pontos. */
    public Page<PontuacaoDTO> extrato(Long usuarioId, Pageable pageable) {
        validarUsuarioExiste(usuarioId);
        return pontuacaoRepository.findByUsuarioIdOrderByDataRegistroDesc(usuarioId, pageable)
                .map(pontuacaoMapper::toDTO);
    }

    /** Ranking (leaderboard) paginado. */
    public Page<LeaderboardItemDTO> leaderboard(Pageable pageable) {
        return pontuacaoRepository.leaderboard(pageable)
                .map(pontuacaoMapper::toLeaderboardDTO);
    }

    /** Resgate de recompensa: debita pontos e registra resgate. */
    @Transactional
    public PontuacaoDTO resgatar(ResgateRequestDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.usuarioId())
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));
        Recompensa recompensa = recompensaRepository.findById(dto.recompensaId())
                .orElseThrow(() -> new NotFoundException("Recompensa não encontrada"));

        // validações
        if (recompensa.getDataExpiracao() != null && recompensa.getDataExpiracao().isBefore(LocalDate.now())) {
            throw new BusinessException("Recompensa expirada");
        }
        if (recompensa.getQuantidadeDisponivel() != null && recompensa.getQuantidadeDisponivel() <= 0) {
            throw new BusinessException("Recompensa sem estoque");
        }
        Integer saldoAtual = pontuacaoRepository.sumPontosByUsuario(usuario.getId());
        int saldo = saldoAtual == null ? 0 : saldoAtual;
        if (saldo < recompensa.getPontosNecessarios()) {
            throw new BusinessException("Saldo insuficiente para resgate");
        }

        // debita pontos (lançamento negativo)
        Pontuacao debito = new Pontuacao();
        debito.setUsuario(usuario);
        debito.setPontos(-recompensa.getPontosNecessarios());
        debito.setDataRegistro(LocalDate.now());
        pontuacaoRepository.save(debito);

        // registra resgate
        UsuarioRecompensa ur = new UsuarioRecompensa();
        ur.setUsuario(usuario);
        ur.setRecompensa(recompensa);
        ur.setDataResgate(LocalDate.now());
        usuarioRecompensaRepository.save(ur);

        // baixa estoque (se houver controle)
        if (recompensa.getQuantidadeDisponivel() != null) {
            recompensa.setQuantidadeDisponivel(recompensa.getQuantidadeDisponivel() - 1);
            recompensaRepository.save(recompensa);
        }

        return pontuacaoMapper.toDTO(debito);
    }

    private void validarUsuarioExiste(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new NotFoundException("Usuário não encontrado");
        }
    }
}
