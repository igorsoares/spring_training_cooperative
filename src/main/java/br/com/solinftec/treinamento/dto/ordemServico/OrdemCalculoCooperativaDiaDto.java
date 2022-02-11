package br.com.solinftec.treinamento.dto.ordemServico;

import java.util.Date;

import br.com.solinftec.treinamento.model.Cooperativa;
import br.com.solinftec.treinamento.model.OrdemServicoModel;
import lombok.Data;

@Data
public class OrdemCalculoCooperativaDiaDto {
    private Long idCooperativa;
    private Double total_aplicacao;
    private Date data_execucao;

    public OrdemCalculoCooperativaDiaDto() {
    }

    public OrdemCalculoCooperativaDiaDto(Long idCooperativa, Double totalAplicacao, Date data) {
        this.idCooperativa = idCooperativa;
        this.total_aplicacao = totalAplicacao;
        this.data_execucao = data;
    }

    public OrdemCalculoCooperativaDiaDto(OrdemServicoModel ordemModel) {
        Cooperativa coop = ordemModel.getCooperativa();
        this.idCooperativa = coop.getId();
        this.total_aplicacao = ordemModel.getTotal_aplicacao();
        this.data_execucao = ordemModel.getData_execucao();
    }
}
