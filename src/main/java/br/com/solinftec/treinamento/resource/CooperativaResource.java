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

import br.com.solinftec.treinamento.dto.cooperativa.GetAllCooperativaDto;
import br.com.solinftec.treinamento.dto.cooperativa.SaveCooperativaDto;
import br.com.solinftec.treinamento.model.Cooperativa;
import br.com.solinftec.treinamento.model.Fazendeiro;
import br.com.solinftec.treinamento.service.CooperativaService;

@RestController
@RequestMapping("/cooperativa")
// @RequiredArgsConstructor
public class CooperativaResource {
    private final CooperativaService service;

    private static final Logger logger = LoggerFactory.getLogger(Cooperativa.class);

    public CooperativaResource(CooperativaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<GetAllCooperativaDto>> getAll() {
        try {
            return ResponseEntity.ok().body(service.getAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/fazendeiros/{idCooperativa}")
    public ResponseEntity<List<Fazendeiro>> getFazendeirosDaCooperativa(
            @PathVariable("idCooperativa") Long idCooperativa) {
        try {
            return ResponseEntity.ok().body(this.service.getFazendeirosDaCooperativa(idCooperativa));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<SaveCooperativaDto> save(@RequestBody @Valid SaveCooperativaDto saveCooperativa) {
        try {
            return ResponseEntity.ok().body(service.save(saveCooperativa));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping
    public ResponseEntity<SaveCooperativaDto> update(@RequestBody SaveCooperativaDto cooperativaDto) {
        try {
            return ResponseEntity.ok().body(service.update(cooperativaDto));
        } catch (Exception e) {
            logger.error("Erro ao atualizar coop : {} ", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long idCoop) {
        try {
            service.deletar(idCoop);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            logger.error("Erro ao deletar coop {} :> {} ", idCoop, e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

}
