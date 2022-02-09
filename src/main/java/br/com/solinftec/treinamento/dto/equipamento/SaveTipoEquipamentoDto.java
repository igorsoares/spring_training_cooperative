package br.com.solinftec.treinamento.dto.equipamento;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.solinftec.treinamento.model.TipoEquipamento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaveTipoEquipamentoDto {
    private Long id;
    private String descricao;

    @JsonIgnore
    public TipoEquipamento getModel() {
        TipoEquipamento tipo = new TipoEquipamento();
        tipo.setDescricao(this.descricao);
        tipo.setId(this.id);
        return tipo;
    }

}
