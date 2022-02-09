package br.com.solinftec.treinamento.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.solinftec.treinamento.dto.TipoServicoDto;
import br.com.solinftec.treinamento.service.TipoServicoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/tiposervico/")
public class TipoServicoResource {

    private final TipoServicoService service;

    @GetMapping("/{idServico}")
    public ResponseEntity<TipoServicoDto> getTipoServicoById(@PathVariable("idServico") Long idServico) {
        try {
            return ResponseEntity.ok().body(service.getById(idServico));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<TipoServicoDto>> getAllTipoServico() {
        try {
            return ResponseEntity.ok().body(service.getAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<TipoServicoDto> postTipoServico(
            @RequestBody @Valid TipoServicoDto tipoServicoDto) {
        try {
            return ResponseEntity.ok().body(this.service.save(tipoServicoDto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{idServico}")
    public ResponseEntity<?> deleteTipoServico(@PathVariable("idServico") Long idServico) {
        try {
            this.service.delete(idServico);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping
    public ResponseEntity<?> updateTipoServico(@RequestBody @Valid TipoServicoDto tipoServicoDto) {
        try {

            return ResponseEntity.ok().body(this.service.update(tipoServicoDto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
