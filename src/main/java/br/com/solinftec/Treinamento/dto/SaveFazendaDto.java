package br.com.solinftec.Treinamento.dto;

import java.util.Optional;

import javax.annotation.PostConstruct;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.solinftec.Treinamento.model.Fazenda;
import br.com.solinftec.Treinamento.model.Fazendeiro;
import br.com.solinftec.Treinamento.service.FazendeiroService;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
// @RequiredArgsConstructor
public class SaveFazendaDto {
    private Long id;
    private String descricao;
    private Double latitude;
    private Double longitude;
    private Double area;
    private Long id_fazendeiro;

    public SaveFazendaDto() {
    }

    @JsonIgnore
    public Fazenda getFazendaModel() throws Exception {
        try {
            Fazenda fazenda = new Fazenda();
            fazenda.setId(this.id);
            fazenda.setLatitude(this.latitude);
            fazenda.setLongitude(this.longitude);
            fazenda.setDescricao(this.descricao);
            fazenda.setArea(this.area);
            return fazenda;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}
