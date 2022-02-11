package br.com.solinftec.treinamento.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.solinftec.treinamento.dto.ordemServico.OrdemCalculoCooperativaDiaDto;
import br.com.solinftec.treinamento.model.OrdemServicoModel;

public interface OrdemServicoRepository extends JpaRepository<OrdemServicoModel, Long> {
    @Query(value = "select os from OrdemServicoModel os where CAST(os.data_execucao as date) = :date")
    Optional<List<OrdemServicoModel>> findByDate(@Param("date") Date date);

    @Query("select new br.com.solinftec.treinamento.dto.ordemServico.OrdemCalculoCooperativaDiaDto(os.cooperativa.id , sum(os.total_aplicacao), cast(os.data_execucao as date)) "
            +
            "from OrdemServicoModel os " +
            "group by os.cooperativa.id , cast(os.data_execucao as date) " +
            "having cast(os.data_execucao as date) = :date")

    Optional<List<OrdemCalculoCooperativaDiaDto>> calcCooperativaByDay(@Param("date") Date date);

}
