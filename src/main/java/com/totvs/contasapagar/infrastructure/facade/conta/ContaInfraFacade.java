package com.totvs.contasapagar.infrastructure.facade.conta;

import com.totvs.contasapagar.infrastructure.repository.conta.entity.ContaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ContaInfraFacade {

    ContaEntity criarConta(ContaEntity conta);
    ContaEntity atualizarConta(Long id, ContaEntity conta);
    Optional<ContaEntity> obterContaPorId(Long id);
    Page<ContaEntity> obterListaContasComFiltro(Pageable pageable, LocalDate dataVencimento, String descricao);
    void alterarSituacaoConta(ContaEntity entity);
    void importCSV(List<ContaEntity> contaList);
    BigDecimal obterValorTotalPagoPorPeriodo(LocalDate inicio, LocalDate fim);
}
