package com.totvs.contasapagar.domain.business.conta;

import com.totvs.contasapagar.domain.conta.model.Conta;
import com.totvs.contasapagar.infrastructure.repository.conta.enums.Situacao;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

public interface ContaDomainBusiness {

    Conta criarConta(Conta conta);

    Conta atualizarConta(Conta contaExistente, Conta contaAtualizada);

    Situacao validarSituacaoConta(String situacao);
    void validarContaParaPagamento(Conta conta);
    Conta pagarConta(Conta conta, LocalDate dataPagamento);
    void importarCSV(MultipartFile file);
}
