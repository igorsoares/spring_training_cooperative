package br.com.solinftec.treinamento.dto.equipamento;

import br.com.solinftec.treinamento.model.Equipamento;
import br.com.solinftec.treinamento.model.TipoEquipamento;
import lombok.Data;

@Data
public class EquipamentoWithTipoDto {
    private Long id;
    private String descricao;
    private Double latitude;
    private Double longitude;
    private Boolean ativo;

    private TipoEquipamento tipoEquipamento;

    public EquipamentoWithTipoDto() {
    }

    public EquipamentoWithTipoDto(Equipamento equipamentoModel) {
        this.id = equipamentoModel.getId();
        this.descricao = equipamentoModel.getDescricao();
        this.latitude = equipamentoModel.getLatitude();
        this.longitude = equipamentoModel.getLongitude();
        this.ativo = equipamentoModel.getAtivo();
        this.tipoEquipamento = equipamentoModel.getTipoEquipamento();
    }
}
