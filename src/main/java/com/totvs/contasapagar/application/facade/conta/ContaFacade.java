package com.totvs.contasapagar.application.facade.conta;


import com.totvs.contasapagar.application.controller.conta.dto.ContaDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

public interface ContaFacade {
    ContaDto criarConta(ContaDto contaDTO);
    ContaDto atualizarConta(Long id, ContaDto contaDTO);
    Optional<ContaDto> obterContaPorId(Long id);
    Page<ContaDto> obterListaContasComFiltro(Pageable pageable, LocalDate dataVencimento, String descricao);
    void alterarSituacaoConta(Long id, String situacao);
    void importCSV(MultipartFile file);
    BigDecimal obterValorTotalPagoPorPeriodo(LocalDate inicio, LocalDate fim);
}
