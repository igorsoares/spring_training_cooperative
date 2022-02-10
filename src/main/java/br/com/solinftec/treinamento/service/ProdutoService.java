package br.com.solinftec.treinamento.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.solinftec.treinamento.configuration.TreinamentoDefaultException;
import br.com.solinftec.treinamento.dto.ProdutoDto;
import br.com.solinftec.treinamento.model.Produto;
import br.com.solinftec.treinamento.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProdutoService {
    private final ProdutoRepository produtoRepository;

    private static final String MSG_NOT_FOUND = "PRODUTO_NOT_FOUND";

    public ProdutoDto getById(Long idProduto) throws TreinamentoDefaultException {
        Optional<Produto> optProduto = produtoRepository.findById(idProduto);
        if (optProduto.isPresent()) {
            return new ProdutoDto(optProduto.get());
        }
        throw new TreinamentoDefaultException(MSG_NOT_FOUND);
    }

    public Produto getModelById(Long idProduto) throws TreinamentoDefaultException {
        return this.produtoRepository.findById(
                idProduto)
                .orElseThrow(() -> new TreinamentoDefaultException(MSG_NOT_FOUND));
    }

    public List<ProdutoDto> getAll() {
        return this.produtoRepository.findAll()
                .stream().map(ProdutoDto::new).collect(Collectors.toList());
    }

    public void deleteProduto(Long idProduto) throws Exception {
        Optional<Produto> optProduto = produtoRepository.findById(idProduto);
        if (optProduto.isPresent()) {
            this.produtoRepository.delete(optProduto.get());
        } else
            throw new Exception(MSG_NOT_FOUND);
    }

    public ProdutoDto saveProduto(ProdutoDto produtoDto) throws Exception {
        try {
            return new ProdutoDto(this.produtoRepository.save(produtoDto.getModel()));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}
