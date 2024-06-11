package com.totvs.contasapagar.application.facade.conta.impl;

import com.totvs.contasapagar.application.controller.conta.dto.ContaDto;
import com.totvs.contasapagar.application.facade.conta.ContaFacade;
import com.totvs.contasapagar.application.mapper.conta.ContaMapper;
import com.totvs.contasapagar.domain.conta.model.Conta;
import com.totvs.contasapagar.domain.facade.conta.ContaDomainFacade;
import com.totvs.contasapagar.infrastructure.repository.conta.entity.ContaEntity;
import com.totvs.contasapagar.infrastructure.repository.conta.enums.Situacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Component
public class ContaFacadeImpl implements ContaFacade {

    private final ContaDomainFacade contaDomainFacade;
    private final ContaMapper mapper;

    public ContaFacadeImpl(ContaDomainFacade contaDomainFacade, ContaMapper mapper) {
        this.contaDomainFacade = contaDomainFacade;
        this.mapper = mapper;
    }


    @Override
    public ContaDto criarConta(ContaDto contaDTO) {
        Conta dto = mapper.toDomain(contaDTO);
        Conta conta = contaDomainFacade.criarConta(dto);
        return mapper.toDto(conta);
    }

    @Override
    public ContaDto atualizarConta(Long id, ContaDto contaDTO) {
        Conta conta = mapper.toDomain(contaDTO);
        Conta contaAtualizada = contaDomainFacade.atualizarConta(id, conta);
        return mapper.toDto(contaAtualizada);
    }

    @Override
    public Optional<ContaDto> obterContaPorId(Long id) {
        return Optional.empty();
    }

    @Override
    public Page<ContaDto> obterListaContasComFiltro(Pageable pageable, LocalDate dataVencimento, String descricao) {
        Page<Conta> contaPage = contaDomainFacade.obterListaContasComFiltro(pageable, dataVencimento, descricao);
        return mapper.toDtoPage(contaPage);
    }

    @Override
    public void alterarSituacaoConta(Long id, String situacao) {
        contaDomainFacade.alterarSituacaoConta(ContaEntity.builder().id(id).situacao(Situacao.valueOf(situacao)).build());
    }

    @Override
    public void importCSV(MultipartFile file) {
        contaDomainFacade.importCSV(file);
    }

    @Override
    public BigDecimal obterValorTotalPagoPorPeriodo(LocalDate inicio, LocalDate fim) {
        return contaDomainFacade.obterValorTotalPagoPorPeriodo(inicio, fim);
    }
}
