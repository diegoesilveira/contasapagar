package com.totvs.contasapagar.domain.business.conta.impl;

import com.totvs.contasapagar.domain.business.conta.ContaDomainBusiness;
import com.totvs.contasapagar.domain.conta.model.Conta;
import com.totvs.contasapagar.infrastructure.exception.InvalidContaStateException;
import com.totvs.contasapagar.infrastructure.repository.conta.enums.Situacao;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

import static com.totvs.contasapagar.infrastructure.util.Message.*;

@Component
public class ContaDomainBusinessImpl implements ContaDomainBusiness {
    @Override
    public Conta criarConta(Conta conta) {
        validarConta(conta);
        conta.setSituacao(Situacao.PENDENTE);
        return conta;
    }

    @Override
    public Conta atualizarConta(Conta contaExistente, Conta contaAtualizada) {
        validarConta(contaAtualizada);
        contaExistente.setDescricao(contaAtualizada.getDescricao());
        contaExistente.setValor(contaAtualizada.getValor());
        contaExistente.setDataVencimento(contaAtualizada.getDataVencimento());
        return contaExistente;
    }

    public Situacao validarSituacaoConta(String situacao) {
        Situacao situacaoEnum;
        try {
            situacaoEnum = Situacao.valueOf(situacao);
        } catch (IllegalArgumentException e) {
            throw new InvalidContaStateException("Situacao inválida: " + situacao);
        }

        if (situacaoEnum == Situacao.PENDENTE || situacaoEnum == Situacao.CANCELADA) {
            throw new InvalidContaStateException(CONTA_ESTA_PENDENTE_OU_CANCELADA);
        }

        return situacaoEnum;
    }

    @Override
    public void validarContaParaPagamento(Conta conta) {
        if (conta.getSituacao() == Situacao.PENDENTE || conta.getSituacao() == Situacao.CANCELADA) {
            throw new InvalidContaStateException(CONTA_ESTA_PENDENTE_OU_CANCELADA);
        }
    }

    @Override
    public Conta pagarConta(Conta conta, LocalDate dataPagamento) {
        validarContaParaPagamento(conta);
        conta.pagar(dataPagamento, conta.getValor());
        return conta;
    }
    @Override
    public void importarCSV(MultipartFile file) {

    }

    protected void validarConta(Conta conta) {
        if (conta.getValor().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(VALOR_CONTA_DEVE_SER_MAIOR_QUE_ZERO);
        }
        if (conta.getDataVencimento().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException(VALOR_VENCIMENTO_NAO_PODE_SER_DO_PASSADO);
        }
    }
}
