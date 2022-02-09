package br.com.solinftec.treinamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.solinftec.treinamento.model.Equipamento;

public interface EquipamentoRepository extends JpaRepository<Equipamento, Long> {
    Equipamento findByDescricao(String descricao);
}
