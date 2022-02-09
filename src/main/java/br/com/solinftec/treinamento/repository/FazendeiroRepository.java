package br.com.solinftec.treinamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.solinftec.treinamento.model.Fazendeiro;

public interface FazendeiroRepository extends JpaRepository<Fazendeiro, Long> {

}
