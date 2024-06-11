package com.totvs.contasapagar.application.controller.conta;

import com.totvs.contasapagar.application.controller.conta.dto.ContaDto;
import com.totvs.contasapagar.application.facade.conta.ContaFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/v1/contas")
public class ContaController {

    private final ContaFacade contaFacade;

    @Autowired
    public ContaController(ContaFacade contaFacade) {
        this.contaFacade = contaFacade;
    }

    @PostMapping
    public ResponseEntity<ContaDto> criarConta(@RequestBody ContaDto contaDto) {
        ContaDto novaConta = contaFacade.criarConta(contaDto);
        return new ResponseEntity<>(novaConta, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContaDto> atualizarConta(@PathVariable Long id, @RequestBody ContaDto contaDto) {
        ContaDto contaAtualizada = contaFacade.atualizarConta(id, contaDto);
        return new ResponseEntity<>(contaAtualizada, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContaDto> obterContaPorId(@PathVariable Long id) {
        Optional<ContaDto> conta = contaFacade.obterContaPorId(id);
        return conta.map(response -> new ResponseEntity<>(response, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<Page<ContaDto>> obterListaContasComFiltro(Pageable pageable,
                                                                    @RequestParam(required = false) LocalDate dataVencimento,
                                                                    @RequestParam(required = false) String descricao) {
        Page<ContaDto> contas = contaFacade.obterListaContasComFiltro(pageable, dataVencimento, descricao);
        return new ResponseEntity<>(contas, HttpStatus.OK);
    }

    @PatchMapping("/{id}/situacao")
    public ResponseEntity<Void> alterarSituacaoConta(@PathVariable Long id, @RequestParam String situacao) {
        contaFacade.alterarSituacaoConta(id, situacao);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/import")
    public ResponseEntity<Void> importCSV(@RequestParam("file") MultipartFile file) {
        contaFacade.importCSV(file);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/total-pago")
    public ResponseEntity<BigDecimal> obterValorTotalPagoPorPeriodo(@RequestParam LocalDate inicio, @RequestParam LocalDate fim) {
        BigDecimal totalPago = contaFacade.obterValorTotalPagoPorPeriodo(inicio, fim);
        return new ResponseEntity<>(totalPago, HttpStatus.OK);
    }

}
