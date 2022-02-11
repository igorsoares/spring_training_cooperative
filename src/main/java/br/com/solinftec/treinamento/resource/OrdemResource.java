package br.com.solinftec.treinamento.resource;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.solinftec.treinamento.configuration.TreinamentoDefaultException;
import br.com.solinftec.treinamento.dto.ordemServico.OrdemCalculoCooperativaDiaDto;
import br.com.solinftec.treinamento.dto.ordemServico.OrdemServicoDto;
import br.com.solinftec.treinamento.service.OrdemServicoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/ordem")
@RequiredArgsConstructor
@Slf4j
public class OrdemResource {
    private final OrdemServicoService service;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @GetMapping(value = "/{idOrdem}")
    public ResponseEntity<OrdemServicoDto> getOrdemById(@PathVariable("idOrdem") Long idOrdem)
            throws TreinamentoDefaultException {
        return ResponseEntity.ok().body(service.getById(idOrdem));

    }

    @GetMapping(value = "/filter")
    public ResponseEntity<List<OrdemServicoDto>> filterOrdemByDate(
            @RequestParam("data") String dataExecucao)
            throws TreinamentoDefaultException {
        LocalDate dtExecucao = LocalDate.parse(dataExecucao, formatter);
        return ResponseEntity.ok().body(service.filterByDate(dtExecucao));
    }

    @GetMapping(value = "/calculoCooperativa")
    public ResponseEntity<List<OrdemCalculoCooperativaDiaDto>> calcCooperativaDay(
            @RequestParam("data") String dataExecucao)
            throws TreinamentoDefaultException {
        LocalDate dtExecucao = LocalDate.parse(dataExecucao, formatter);
        return ResponseEntity.ok().body(service.calcCooperativaByDay(dtExecucao));
    }

    @DeleteMapping(value = "/{idOrdem}")
    public ResponseEntity<?> deleteOrdemById(@PathVariable("idOrdem") Long idOrdem)
            throws TreinamentoDefaultException {
        this.service.deleteOrdem(idOrdem);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<OrdemServicoDto>> getAllFazenda() {
        try {
            return ResponseEntity.ok().body(this.service.getAll());
        } catch (Exception e) {
            log.error("Erro em obter todas as ordens:  {} ", e.getMessage());
            return ResponseEntity.badRequest().build();
        }

    }

    @PostMapping
    public ResponseEntity<OrdemServicoDto> saveOrdem(@RequestBody @Valid OrdemServicoDto ordemServicoDto)
            throws TreinamentoDefaultException {
        return ResponseEntity.ok().body(this.service.saveOrdem(ordemServicoDto));

    }

}
