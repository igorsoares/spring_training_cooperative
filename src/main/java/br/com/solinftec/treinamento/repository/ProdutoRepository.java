package br.com.solinftec.treinamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.solinftec.treinamento.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}