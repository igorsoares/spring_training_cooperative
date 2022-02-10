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

import br.com.solinftec.treinamento.configuration.TreinamentoDefaultException;
import br.com.solinftec.treinamento.dto.equipamento.EquipamentoWithTipoDto;
import br.com.solinftec.treinamento.dto.equipamento.EquipamentoWithTipoIdDto;
import br.com.solinftec.treinamento.model.Equipamento;
import br.com.solinftec.treinamento.service.EquipamentoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/equipamento")
@RequiredArgsConstructor
public class EquipamentoResource {

    private final EquipamentoService equipamentoService;

    @GetMapping
    public ResponseEntity<List<Equipamento>> getAll() {
        try {
            return ResponseEntity.ok().body(equipamentoService.getAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipamentoWithTipoDto> getById(@PathVariable("id") Long id)
            throws TreinamentoDefaultException {
        return ResponseEntity.ok().body(equipamentoService.getById(id));
    }

    @PutMapping
    public ResponseEntity<Equipamento> update(@RequestBody EquipamentoWithTipoIdDto update) {
        try {
            return ResponseEntity.ok().body(equipamentoService.update(update));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<Equipamento> save(@RequestBody EquipamentoWithTipoIdDto save) {
        try {
            return ResponseEntity.ok().body(equipamentoService.save(save));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{idEquipamento}")
    public ResponseEntity<Equipamento> delete(@PathVariable("idEquipamento") Long idEquipamento) {
        try {
            equipamentoService.delete(idEquipamento);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
