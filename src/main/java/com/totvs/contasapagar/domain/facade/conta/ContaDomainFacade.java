package com.totvs.contasapagar.domain.facade.conta;

import com.totvs.contasapagar.domain.conta.model.Conta;
import com.totvs.contasapagar.infrastructure.repository.conta.entity.ContaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ContaDomainFacade {
    Conta criarConta(Conta conta);
    Conta atualizarConta(Long id, Conta conta);
    Optional<Conta> obterContaPorId(Long id);
    Page<Conta> obterListaContasComFiltro(Pageable pageable, LocalDate dataVencimento, String descricao);
    void alterarSituacaoConta(ContaEntity entity);
    void importCSV(MultipartFile file);
    void importCSV(List<Conta> contaList);
    BigDecimal obterValorTotalPagoPorPeriodo(LocalDate inicio, LocalDate fim);
}
