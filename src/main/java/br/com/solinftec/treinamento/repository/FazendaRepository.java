package br.com.solinftec.treinamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.solinftec.treinamento.model.Fazenda;

public interface FazendaRepository extends JpaRepository<Fazenda, Long> {

}
