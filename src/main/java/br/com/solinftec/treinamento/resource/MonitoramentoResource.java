package br.com.solinftec.treinamento.resource;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
