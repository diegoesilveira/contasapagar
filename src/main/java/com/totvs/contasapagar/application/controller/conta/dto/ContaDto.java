package com.totvs.contasapagar.application.controller.conta.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ContaDto(
        Long id,
        LocalDate dataVencimento,
        LocalDate dataPagamento,
        BigDecimal valor,
        String descricao,
        String situacao
) {
}
