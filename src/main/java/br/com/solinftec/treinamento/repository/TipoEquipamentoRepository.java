package br.com.solinftec.treinamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.solinftec.treinamento.model.TipoEquipamento;

public interface TipoEquipamentoRepository extends JpaRepository<TipoEquipamento, Long> {
    TipoEquipamento findByDescricao(String descricao);
}
