package br.com.solinftec.treinamento.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.solinftec.treinamento.dto.fazendeiro.FazendeiroWithFazendaDto;
import br.com.solinftec.treinamento.service.FazendeiroService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/fazendeiro")
@RequiredArgsConstructor
public class FazendeiroResource {

    private final FazendeiroService service;

    @GetMapping("/{idFazendeiro}")
    public ResponseEntity<FazendeiroWithFazendaDto> getFazendeiro(@PathVariable("idFazendeiro") Long idFazendeiro) {
        try {
            return ResponseEntity.ok().body(service.getFazendeiro(idFazendeiro));
        } catch (Exception e) {
            if (e.getMessage().equals("FAZENDEIRO_NOT_FOUND"))
                return ResponseEntity.notFound().build();
            return ResponseEntity.badRequest().build();
        }
    }
}