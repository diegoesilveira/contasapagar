package com.totvs.contasapagar.infrastructure.facade.conta.impl;

import com.totvs.contasapagar.infrastructure.facade.conta.ContaInfraFacade;
import com.totvs.contasapagar.infrastructure.repository.conta.ContaRepository;
import com.totvs.contasapagar.infrastructure.repository.conta.entity.ContaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
public class ContaInfraFacadeImpl implements ContaInfraFacade {

    private final ContaRepository repository;

    public ContaInfraFacadeImpl(ContaRepository repository) {
        this.repository = repository;
    }


    @Override
    public ContaEntity criarConta(ContaEntity conta) {
        return repository.save(conta);
    }

    @Override
    public ContaEntity atualizarConta(Long id, ContaEntity conta) {
        return repository.save(conta);
    }

    @Override
    public Optional<ContaEntity> obterContaPorId(Long id) {
        return repository.findById(id);
    }

    @Override
    public Page<ContaEntity> obterListaContasComFiltro(Pageable pageable, LocalDate dataVencimento, String descricao) {
        return repository.obterListaContasComFiltro(pageable, dataVencimento, descricao);
    }

    @Override
    public void alterarSituacaoConta(ContaEntity entity) {
        repository.save(entity);
    }

    @Override
    public void importCSV(List<ContaEntity> contaList) {
        repository.saveAll(contaList);
    }

    @Override
    public BigDecimal obterValorTotalPagoPorPeriodo(LocalDate inicio, LocalDate fim) {
        return repository.findTotalValorPagoPorPeriodo(inicio, fim);
    }
}
