package com.totvs.contasapagar.domain.conta.service.csv;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.totvs.contasapagar.domain.conta.model.Conta;
import com.totvs.contasapagar.domain.conta.model.ContaCsv;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.stream.Collectors;

import static com.totvs.contasapagar.infrastructure.util.Message.ERRO_AO_LER_ARQUIVO_CSV;

@Component
public class LerContasDoCSV {

    public List<Conta> lerCsv(MultipartFile file) {
        try (Reader reader = new InputStreamReader(file.getInputStream())) {
            CsvToBean<ContaCsv> csvToBean = new CsvToBeanBuilder<ContaCsv>(reader)
                    .withType(ContaCsv.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            List<ContaCsv> contaCSVList = csvToBean.parse();

            return contaCSVList.stream()
                    .map(this::mapToConta)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(ERRO_AO_LER_ARQUIVO_CSV, e);
        }
    }

    private Conta mapToConta(ContaCsv contaCSV) {
        return Conta.builder()
                .dataVencimento(contaCSV.getDataVencimento())
                .dataPagamento(contaCSV.getDataPagamento())
                .valor(contaCSV.getValor())
                .descricao(contaCSV.getDescricao())
                .situacao(contaCSV.getSituacao())
                .build();
    }
}
