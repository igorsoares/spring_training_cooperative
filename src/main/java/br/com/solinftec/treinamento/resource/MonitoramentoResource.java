package br.com.solinftec.treinamento.resource;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.solinftec.treinamento.configuration.TreinamentoDefaultException;
import br.com.solinftec.treinamento.dto.monitoramento.MonitoramentoGetDto;
import br.com.solinftec.treinamento.dto.monitoramento.MonitoramentoIdDto;
import br.com.solinftec.treinamento.model.Monitoramento;
import br.com.solinftec.treinamento.service.MonitoramentoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/monitoramento")
@RequiredArgsConstructor
public class MonitoramentoResource {

    private final MonitoramentoService monitoramentoService;

    @GetMapping
    public ResponseEntity<List<Monitoramento>> getAll() {
        try {
            return ResponseEntity.ok().body(monitoramentoService.getAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{idMonitoramento}")
    public ResponseEntity<MonitoramentoGetDto> getById(@PathVariable("idMonitoramento") Long idMonitoramento)
            throws TreinamentoDefaultException {
        return ResponseEntity.ok().body(this.monitoramentoService.getById(idMonitoramento));
    }

    @PostMapping
    public ResponseEntity<MonitoramentoGetDto> saveMonitoramento(@RequestBody MonitoramentoIdDto monitoramento)
            throws TreinamentoDefaultException {
        return ResponseEntity.ok().body(this.monitoramentoService.saveMonitoramento(monitoramento));
    }

    @DeleteMapping("/{idMonitoramento}")
    public ResponseEntity<?> delete(@PathVariable("idMonitoramento") Long idMonitoramento)
            throws TreinamentoDefaultException {
        this.monitoramentoService.delete(idMonitoramento);
        return ResponseEntity.ok().build();
    }
}
