package com.totvs.contasapagar.domain.conta.model;

import com.totvs.contasapagar.infrastructure.repository.conta.enums.Situacao;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Conta {

    private Long id;
    private LocalDate dataVencimento;
    private LocalDate dataPagamento;
    private BigDecimal valor;
    private String descricao;

    @Enumerated(EnumType.STRING)
    private Situacao situacao;

    public void pagar(LocalDate dataPagamento, BigDecimal valor) {
        this.dataPagamento = dataPagamento;
        this.valor = valor;
        this.situacao = Situacao.PAGA;
    }
}
