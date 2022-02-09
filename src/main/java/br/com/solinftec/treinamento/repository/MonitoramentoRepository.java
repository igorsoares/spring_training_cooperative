package br.com.solinftec.treinamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.solinftec.treinamento.model.Monitoramento;

public interface MonitoramentoRepository extends JpaRepository<Monitoramento, Long> {

}
