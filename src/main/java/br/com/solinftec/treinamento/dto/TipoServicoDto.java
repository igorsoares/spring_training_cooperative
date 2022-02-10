package br.com.solinftec.treinamento.dto;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.solinftec.treinamento.model.TipoServico;
import lombok.Data;

@Data
public class TipoServicoDto {
    private Long id;
    @NotNull(message = "DESCRICAO_CANT_BE_NULL")
    private String descricao;

    public TipoServicoDto() {

    }

    public TipoServicoDto(TipoServico tipoServicoModel) {
        this.id = tipoServicoModel.getId();
        this.descricao = tipoServicoModel.getDescricao();
    }

    @JsonIgnore
    public TipoServico getModel() {
        TipoServico tipoServico = new TipoServico();
        tipoServico.setDescricao(descricao);
        tipoServico.setId(id);
        return tipoServico;
    }

}
