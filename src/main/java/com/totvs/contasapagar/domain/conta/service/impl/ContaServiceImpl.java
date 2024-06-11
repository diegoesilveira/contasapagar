package com.totvs.contasapagar.domain.conta.service.impl;

import com.totvs.contasapagar.application.mapper.conta.ContaMapper;
import com.totvs.contasapagar.domain.business.conta.ContaDomainBusiness;
import com.totvs.contasapagar.domain.conta.model.Conta;
import com.totvs.contasapagar.domain.conta.service.ContaService;
import com.totvs.contasapagar.domain.conta.service.csv.LerContasDoCSV;
import com.totvs.contasapagar.domain.facade.conta.ContaDomainFacade;
import com.totvs.contasapagar.infrastructure.exception.InvalidContaStateException;
import com.totvs.contasapagar.infrastructure.repository.conta.enums.Situacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.totvs.contasapagar.infrastructure.util.Message.CONTA_NAO_ENCONTRADA;

@Service
public class ContaServiceImpl implements ContaService {

    private final ContaDomainBusiness domainBusiness;
    private final ContaDomainFacade domainFacade;

    private final ContaMapper mapper;

    private final LerContasDoCSV lerContasDoCSV;

    public ContaServiceImpl(ContaDomainBusiness domainBusiness, ContaDomainFacade domainFacade, ContaMapper mapper, LerContasDoCSV lerContasDoCSV) {
        this.domainBusiness = domainBusiness;
        this.domainFacade = domainFacade;
        this.mapper = mapper;
        this.lerContasDoCSV = lerContasDoCSV;
    }

    @Override
    public Conta criarConta(Conta conta) {
        Conta criarConta = domainBusiness.criarConta(conta);
        return domainFacade.criarConta(criarConta);
    }

    @Override
    public Conta atualizarConta(Long id, Conta contaAtualizada) {
        Conta contaExistente = domainFacade.obterContaPorId(id)
                .orElseThrow(() -> new InvalidContaStateException(CONTA_NAO_ENCONTRADA));
        Conta validarAtualizacao = domainBusiness.atualizarConta(contaAtualizada, contaExistente);
        return domainFacade.atualizarConta(validarAtualizacao.getId(), validarAtualizacao);
    }

    @Override
    public Optional<Conta> obterContaPorId(Long id) {
        return domainFacade.obterContaPorId(id);
    }

    @Override
    public Page<Conta> obterListaContasComFiltro(Pageable pageable, LocalDate dataVencimento, String descricao) {
        return domainFacade.obterListaContasComFiltro(pageable, dataVencimento, descricao);
    }

    @Override
    public void alterarSituacaoConta(Long id, String situacao) {
        Conta conta = domainFacade.obterContaPorId(id)
                .orElseThrow(() -> new IllegalArgumentException(CONTA_NAO_ENCONTRADA));
        Situacao situacaoConta = domainBusiness.validarSituacaoConta(situacao);
        conta.setSituacao(situacaoConta);

        domainFacade.alterarSituacaoConta(mapper.toEntity(conta));
    }

    @Override
    public void importCSV(MultipartFile file) {
        List<Conta> contaList = lerContasDoCSV.lerCsv(file);
        domainFacade.importCSV(contaList);
    }

    @Override
    public BigDecimal obterValorTotalPagoPorPeriodo(LocalDate inicio, LocalDate fim) {
        return domainFacade.obterValorTotalPagoPorPeriodo(inicio, fim);
    }
}
