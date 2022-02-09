package br.com.solinftec.treinamento.dto.fazenda;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.solinftec.treinamento.dto.fazendeiro.FazendeiroDto;
import br.com.solinftec.treinamento.model.Fazenda;
import lombok.Data;

@Data
public class FazendaWithFazendeiroDto {
    private Long id;
    @NotNull(message = "DESCRICAO_CANT_BE_NULL")
    private String descricao;
    @NotNull(message = "LATITUDE_CANT_BE_NULL")
    private Double latitude;
    @NotNull(message = "LONGITUDE_CANT_BE_NULL")
    private Double longitude;
    @NotNull(message = "AREA_CANT_BE_NULL")
    private Double area;
    private FazendeiroDto fazendeiro;

    public FazendaWithFazendeiroDto() {
    }

    public FazendaWithFazendeiroDto(Fazenda fazenda) {
        this.id = fazenda.getId();
        this.descricao = fazenda.getDescricao();
        this.latitude = fazenda.getLatitude();
        this.longitude = fazenda.getLongitude();
        this.area = fazenda.getArea();
        this.fazendeiro = new FazendeiroDto(fazenda.getFazendeiro());
    }

    @JsonIgnore
    public Fazenda getFazendaModel() {
        Fazenda fazenda = new Fazenda();
        fazenda.setArea(this.area);
        fazenda.setDescricao(this.descricao);
        fazenda.setId(this.id);
        fazenda.setLatitude(this.latitude);
        fazenda.setLongitude(this.longitude);
        fazenda.setFazendeiro(this.fazendeiro.getFazendeiroModel());
        return fazenda;
    }
}
