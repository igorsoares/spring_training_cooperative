package br.com.solinftec.treinamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.solinftec.treinamento.model.TipoServico;

@Repository
public interface TipoServicoRepository extends JpaRepository<TipoServico, Long> {
    public TipoServico findByDescricao(String descricao);
}
