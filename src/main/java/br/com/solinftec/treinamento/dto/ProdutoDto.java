package br.com.solinftec.treinamento.dto;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.solinftec.treinamento.model.Produto;
import lombok.Data;

@Data
public class ProdutoDto {
    private Long id;
    @NotNull(message = "DESCRICAO_CANNOT_BE_NULL")
    private String descricao;
    @NotNull(message = "LATITUDE_CANNOT_BE_NULL")
    private Double latitude;
    @NotNull(message = "LONGITUDE_CANNOT_BE_NULL")
    private Double longitude;
    @NotNull(message = "ATIVO_CANNOT_BE_NULL")
    private Boolean ativo;

    public ProdutoDto() {
    }

    public ProdutoDto(Produto produto) {
        this.id = produto.getId();
        this.descricao = produto.getDescricao();
        this.latitude = produto.getLatitude();
        this.longitude = produto.getLongitude();
        this.ativo = produto.getAtivo();
    }

    @JsonIgnore
    public Produto getModel() {
        Produto produto = new Produto();
        produto.setAtivo(ativo);
        produto.setDescricao(descricao);
        produto.setId(id);
        produto.setLatitude(latitude);
        produto.setLongitude(longitude);
        return produto;
    }
}
