package br.com.solinftec.treinamento.dto.equipamento;

import br.com.solinftec.treinamento.model.Equipamento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EquipamentoWithTipoIdDto {
    private Long id;
    private String descricao;
    private Double latitude;
    private Double longitude;
    private Boolean ativo;

    private Long tipoEquipamento;

    public Equipamento getModel() {
        Equipamento eq = new Equipamento();
        eq.setAtivo(this.ativo);
        eq.setDescricao(this.descricao);
        eq.setId(this.id);
        eq.setLatitude(this.latitude);
        eq.setLongitude(this.longitude);
        return eq;
    }
}
