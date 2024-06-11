package com.totvs.contasapagar.domain.conta.model;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import com.totvs.contasapagar.infrastructure.repository.conta.enums.Situacao;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class ContaCsv {

    @CsvBindByName(column = "dataVencimento")
    @CsvDate("yyyy-MM-dd")
    private LocalDate dataVencimento;

    @CsvBindByName(column = "dataPagamento")
    @CsvDate("yyyy-MM-dd")
    private LocalDate dataPagamento;

    @CsvBindByName(column = "valor")
    private BigDecimal valor;

    @CsvBindByName(column = "descricao")
    private String descricao;

    @CsvBindByName(column = "situacao")
    private Situacao situacao;
}
