package br.com.solinftec.treinamento.resource;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.solinftec.treinamento.dto.fazenda.FazendaWithFazendeiroDto;
import br.com.solinftec.treinamento.model.Fazenda;
import br.com.solinftec.treinamento.service.FazendaService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/fazenda")
@RequiredArgsConstructor
public class FazendaResource {

    private final FazendaService fazendaService;

    private static final Logger logger = LoggerFactory.getLogger(Fazenda.class);

    @GetMapping(value = "/{idFazenda}")
    public ResponseEntity<FazendaWithFazendeiroDto> getFazendaById(@PathVariable("idFazenda") Long idFazenda) {
        try {
            return ResponseEntity.ok().body(this.fazendaService.getFazendaById(idFazenda));
        } catch (Exception e) {
            logger.error("Erro em obter a fazenda {} : {} ", idFazenda, e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<FazendaWithFazendeiroDto>> getAllFazenda() {
        try {
            return ResponseEntity.ok().body(this.fazendaService.getAll());
        } catch (Exception e) {
            logger.error("Erro em obter as fazendas:  {} ", e.getMessage());
            return ResponseEntity.badRequest().build();
        }

    }

    @PutMapping
    public ResponseEntity<FazendaWithFazendeiroDto> updateFazenda(@RequestBody FazendaWithFazendeiroDto fazendaDto) {
        try {
            return ResponseEntity.ok().body(this.fazendaService.updateFazenda(fazendaDto));
        } catch (Exception e) {
            logger.error("Erro ao adicionar a fazenda {} : {} ", fazendaDto.getId(), e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<FazendaWithFazendeiroDto> postFazenda(
            @RequestBody @Valid FazendaWithFazendeiroDto fazendaDto) {
        try {
            return ResponseEntity.ok().body(this.fazendaService.saveFazenda(fazendaDto));
        } catch (Exception e) {
            logger.error("Erro ao adicionar a fazenda {} : {} ", fazendaDto.getId(), e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping(value = "/{idFazenda}")
    public ResponseEntity<?> deleteFazenda(@PathVariable("idFazenda") Long idFazenda) {
        try {
            this.fazendaService.deleteFazenda(idFazenda);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            logger.error("Erro ao deletar a fazenda {} : {} ", idFazenda, e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

}
