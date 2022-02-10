package br.com.solinftec.treinamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.solinftec.treinamento.model.OrdemServicoModel;

public interface OrdemServicoRepository extends JpaRepository<OrdemServicoModel, Long> {

}
