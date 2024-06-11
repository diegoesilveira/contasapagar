package com.totvs.contasapagar.infrastructure.repository.conta;

import com.totvs.contasapagar.infrastructure.repository.conta.entity.ContaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;

@Repository
public interface ContaRepository extends JpaRepository<ContaEntity, Long> {

    @Query("SELECT c FROM ContaEntity c WHERE " +
            "(:dataVencimento IS NULL OR c.dataVencimento = :dataVencimento) AND " +
            "(:descricao IS NULL OR c.descricao LIKE %:descricao%)")
    Page<ContaEntity> obterListaContasComFiltro(Pageable pageable, @Param("dataVencimento") LocalDate dataVencimento, @Param("descricao") String descricao);


    @Query("SELECT SUM(c.valor) FROM ContaEntity c WHERE c.situacao = 'PAGA' AND c.dataPagamento BETWEEN :inicio AND :fim")
    BigDecimal findTotalValorPagoPorPeriodo(LocalDate inicio, LocalDate fim);
}
