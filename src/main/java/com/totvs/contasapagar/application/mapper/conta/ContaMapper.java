package com.totvs.contasapagar.application.mapper.conta;


import com.totvs.contasapagar.application.controller.conta.dto.ContaDto;
import com.totvs.contasapagar.domain.conta.model.Conta;
import com.totvs.contasapagar.infrastructure.repository.conta.entity.ContaEntity;
import com.totvs.contasapagar.infrastructure.repository.conta.enums.Situacao;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ContaMapper {


    // Converte de Conta para ContaEntity
    public ContaEntity toEntity(Conta conta) {
        if (conta == null) {
            return null;
        }

        return ContaEntity.builder()
                .id(conta.getId())
                .dataVencimento(conta.getDataVencimento())
                .dataPagamento(conta.getDataPagamento())
                .valor(conta.getValor())
                .descricao(conta.getDescricao())
                .situacao(conta.getSituacao())
                .build();
    }

    // Converte de ContaDto para Conta
    public static Conta toDomain(ContaDto contaDto) {
        if (contaDto == null) {
            return null;
        }

        return Conta.builder()
                .id(contaDto.id())
                .dataVencimento(contaDto.dataVencimento())
                .dataPagamento(contaDto.dataPagamento())
                .valor(contaDto.valor())
                .descricao(contaDto.descricao())
                .situacao(Situacao.valueOf(contaDto.situacao()))
                .build();
    }

    // Converte de Conta para ContaDto
    public ContaDto toDto(Conta conta) {
        if (conta == null) {
            return null;
        }

        return new ContaDto(
                conta.getId(),
                conta.getDataVencimento(),
                conta.getDataPagamento(),
                conta.getValor(),
                conta.getDescricao(),
                conta.getSituacao().toString()
        );
    }

    // Converte de ContaEntity para Conta
    public Conta toDomain(ContaEntity contaEntity) {
        if (contaEntity == null) {
            return null;
        }

        return Conta.builder()
                .id(contaEntity.getId())
                .dataVencimento(contaEntity.getDataVencimento())
                .dataPagamento(contaEntity.getDataPagamento())
                .valor(contaEntity.getValor())
                .descricao(contaEntity.getDescricao())
                .situacao(contaEntity.getSituacao())
                .build();
    }

    // Converte de ContaEntity para ContaDto
    public static ContaDto toDto(ContaEntity contaEntity) {
        if (contaEntity == null) {
            return null;
        }

        return new ContaDto(
                contaEntity.getId(),
                contaEntity.getDataVencimento(),
                contaEntity.getDataPagamento(),
                contaEntity.getValor(),
                contaEntity.getDescricao(),
                contaEntity.getSituacao().toString()
        );
    }

    public static Conta toConta(ContaEntity contaEntity) {
        if (contaEntity == null) {
            return null;
        }

        return new Conta(
                contaEntity.getId(),
                contaEntity.getDataVencimento(),
                contaEntity.getDataPagamento(),
                contaEntity.getValor(),
                contaEntity.getDescricao(),
                contaEntity.getSituacao()
        );
    }


    public Optional<Conta> toModelOptional(Optional<ContaEntity> entity) {
        return entity.map(this::toModel);
    }

    private Conta toModel(ContaEntity entity) {
        if (entity == null) {
            return null;
        }
        return new Conta(
                entity.getId(),
                entity.getDataVencimento(),
                entity.getDataPagamento(),
                entity.getValor(),
                entity.getDescricao(),
                entity.getSituacao()
        );
    }

    public List<ContaEntity> toDomainList(List<Conta> contaList) {
        if (contaList == null) {
            return null;
        }
        return contaList.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }


    public Page<ContaDto> toDtoPage(Page<Conta> contaPage) {
        return contaPage.map(this::toDto);
    }



}
