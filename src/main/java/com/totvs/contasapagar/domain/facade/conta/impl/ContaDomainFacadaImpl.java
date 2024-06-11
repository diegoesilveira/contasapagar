package com.totvs.contasapagar.domain.facade.conta.impl;

import com.totvs.contasapagar.application.mapper.conta.ContaMapper;
import com.totvs.contasapagar.domain.conta.model.Conta;
import com.totvs.contasapagar.domain.facade.conta.ContaDomainFacade;
import com.totvs.contasapagar.infrastructure.facade.conta.ContaInfraFacade;
import com.totvs.contasapagar.infrastructure.repository.conta.entity.ContaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
public class ContaDomainFacadaImpl implements ContaDomainFacade {

    private final ContaInfraFacade infraFacade;
    private final ContaMapper mapper;

    public ContaDomainFacadaImpl(ContaInfraFacade infraFacade, ContaMapper mapper) {
        this.infraFacade = infraFacade;
        this.mapper = mapper;
    }

    @Override
    public Conta criarConta(Conta conta) {
        ContaEntity contaEntity = mapper.toEntity(conta);
        ContaEntity criarConta = infraFacade.criarConta(contaEntity);
        return ContaMapper.toConta(criarConta);
    }

    @Override
    public Conta atualizarConta(Long id, Conta conta) {
        ContaEntity toDomain = mapper.toEntity(conta);
        ContaEntity entity = infraFacade.atualizarConta(id, toDomain);
        return mapper.toDomain(entity);
    }

    @Override
    public Optional<Conta> obterContaPorId(Long id) {
        return mapper.toModelOptional(infraFacade.obterContaPorId(id));
    }

    @Override
    public Page<Conta> obterListaContasComFiltro(Pageable pageable, LocalDate dataVencimento, String descricao) {
        Page<ContaEntity> contaEntities = infraFacade.obterListaContasComFiltro(pageable, dataVencimento, descricao);
        return contaEntities.map(mapper::toDomain);
    }
    @Override
    public void alterarSituacaoConta(ContaEntity entity) {
        infraFacade.alterarSituacaoConta(entity);
    }

    @Override
    public void importCSV(MultipartFile file) {

    }

    public void importCSV(List<Conta> contaList) {
        List<ContaEntity> contaEntityList = mapper.toDomainList(contaList);
        infraFacade.importCSV(contaEntityList);
    }

    @Override
    public BigDecimal obterValorTotalPagoPorPeriodo(LocalDate inicio, LocalDate fim) {
        return infraFacade.obterValorTotalPagoPorPeriodo(inicio, fim);
    }
}
