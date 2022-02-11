package br.com.solinftec.treinamento.dto.ordemServico;

import java.util.Date;

import javax.validation.constraints.NotNull;

import br.com.solinftec.treinamento.model.OrdemServicoModel;
import lombok.Data;

@Data
public class OrdemServicoDto {
    private Long id;
    @NotNull(message = "IDCOOPERATIVA_CANT_BE_NULL")
    private Long cooperativa;
    @NotNull(message = "IDFAZENDA_CANT_BE_NULL")
    private Long fazenda;
    @NotNull(message = "IDEQUIPAMENTO_CANT_BE_NULL")
    private Long equipamento;
    @NotNull(message = "IDPRODUTO_CANT_BE_NULL")
    private Long produto;
    @NotNull(message = "IDTIPOSERVICO_CANT_BE_NULL")
    private Long tipoServico;

    private Date data_execucao;
    @NotNull(message = "RATE_CANT_BE_NULL")
    private Double rate_aplicacao;
    private Double total_aplicacao;

    public OrdemServicoDto(OrdemServicoModel ordemModel) {
        this.id = ordemModel.getId();
        this.cooperativa = ordemModel.getCooperativa().getId();
        this.fazenda = ordemModel.getFazenda().getId();
        this.equipamento = ordemModel.getEquipamento().getId();
        this.produto = ordemModel.getProduto().getId();
        this.tipoServico = ordemModel.getTipo_servico().getId();
        this.rate_aplicacao = ordemModel.getRate_aplicacao();
        this.total_aplicacao = ordemModel.getTotal_aplicacao();
        this.data_execucao = ordemModel.getData_execucao();
    }

    public OrdemServicoDto() {

    }
}
