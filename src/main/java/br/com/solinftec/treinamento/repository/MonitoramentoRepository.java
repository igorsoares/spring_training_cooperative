package br.com.solinftec.treinamento.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.solinftec.treinamento.model.Monitoramento;

public interface MonitoramentoRepository extends JpaRepository<Monitoramento, Long> {
    @Query("select m from Monitoramento m where m.equipamento.id=:idEquipamento order by m.data_hora")
    Optional<Monitoramento> findMonitoramentoByEquipamentoId(@Param("idEquipamento") Long idEquipamento);
}
