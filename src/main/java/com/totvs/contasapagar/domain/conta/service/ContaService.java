package com.totvs.contasapagar.domain.conta.service;

import com.totvs.contasapagar.domain.conta.model.Conta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

public interface ContaService {

    Conta criarConta(Conta conta);
    Conta atualizarConta(Long id, Conta contaAtualizada);
    Optional<Conta> obterContaPorId(Long id);
    Page<Conta> obterListaContasComFiltro(Pageable pageable, LocalDate dataVencimento, String descricao);
    void alterarSituacaoConta(Long id, String situacao);
    void importCSV(MultipartFile file);
    BigDecimal obterValorTotalPagoPorPeriodo(LocalDate inicio, LocalDate fim);
}
