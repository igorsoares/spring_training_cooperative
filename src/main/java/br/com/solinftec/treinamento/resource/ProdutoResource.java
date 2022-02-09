package br.com.solinftec.treinamento.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.solinftec.treinamento.dto.ProdutoDto;
import br.com.solinftec.treinamento.service.ProdutoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/produto/")
@RequiredArgsConstructor
public class ProdutoResource {
    private final ProdutoService produtoService;

    @GetMapping("/{idProduto}")
    public ResponseEntity<ProdutoDto> getTipoServicoById(@PathVariable("idProduto") Long idProduto) {
        try {
            return ResponseEntity.ok().body(produtoService.getById(idProduto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<ProdutoDto>> getAll() {
        try {
            return ResponseEntity.ok().body(produtoService.getAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<ProdutoDto> postFazenda(
            @RequestBody @Valid ProdutoDto produtoDto) {
        try {
            return ResponseEntity.ok().body(this.produtoService.saveProduto(produtoDto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping(value = "/{idProduto}")
    public ResponseEntity<?> deleteProduto(@PathVariable("idProduto") Long idProduto) {
        try {
            this.produtoService.deleteProduto(idProduto);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
