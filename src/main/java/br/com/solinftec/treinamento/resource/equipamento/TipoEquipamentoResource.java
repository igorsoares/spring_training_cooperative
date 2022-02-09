package br.com.solinftec.treinamento.resource.equipamento;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.solinftec.treinamento.dto.equipamento.SaveTipoEquipamentoDto;
import br.com.solinftec.treinamento.model.TipoEquipamento;
import br.com.solinftec.treinamento.service.TipoEquipamentoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/tipoequipamento/")
@RequiredArgsConstructor
public class TipoEquipamentoResource {

    private final TipoEquipamentoService tipoEquipamentoService;

    @GetMapping
    public ResponseEntity<List<TipoEquipamento>> getAll() {
        try {
            return ResponseEntity.ok().body(tipoEquipamentoService.getAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoEquipamento> getById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok().body(tipoEquipamentoService.getById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<TipoEquipamento> save(@RequestBody SaveTipoEquipamentoDto save) {
        try {
            return ResponseEntity.ok().body(tipoEquipamentoService.save(save));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{idTipoEquipamento}")
    public ResponseEntity<?> delete(@PathVariable("idTipoEquipamento") Long id) {
        try {
            tipoEquipamentoService.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping
    public ResponseEntity<TipoEquipamento> update(@RequestBody SaveTipoEquipamentoDto update) {
        try {
            tipoEquipamentoService.update(update);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
