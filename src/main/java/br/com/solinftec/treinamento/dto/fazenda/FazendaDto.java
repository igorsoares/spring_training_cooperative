package br.com.solinftec.treinamento.dto.fazenda;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.solinftec.treinamento.configuration.TreinamentoDefaultException;
import br.com.solinftec.treinamento.model.Fazenda;
import lombok.Data;

@Data
public class FazendaDto {
    private Long id;
    private String descricao;
    private Double latitude;
    private Double longitude;
    private Double area;

    public FazendaDto(Fazenda fazenda) {
        this.id = fazenda.getId();
        this.descricao = fazenda.getDescricao();
        this.area = fazenda.getArea();
        this.latitude = fazenda.getLatitude();
        this.longitude = fazenda.getLongitude();
    }

    @JsonIgnore
    public Fazenda getFazendaModel() throws TreinamentoDefaultException {
        try {
            Fazenda fazenda = new Fazenda();
            fazenda.setId(this.id);
            fazenda.setLatitude(this.latitude);
            fazenda.setLongitude(this.longitude);
            fazenda.setDescricao(this.descricao);
            fazenda.setArea(this.area);
            return fazenda;
        } catch (Exception e) {
            throw new TreinamentoDefaultException(e.getMessage());
        }
    }

}
