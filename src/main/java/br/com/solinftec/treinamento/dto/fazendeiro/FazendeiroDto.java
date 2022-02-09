package br.com.solinftec.treinamento.dto.fazendeiro;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.solinftec.treinamento.model.Fazendeiro;
import lombok.Data;

@Data

public class FazendeiroDto {
    private Long id;
    private String nome;
    private String email;
    private String telefone;

    public FazendeiroDto() {
    }

    public FazendeiroDto(Fazendeiro fazendeiro) {
        this.id = fazendeiro.getId();
        this.nome = fazendeiro.getNome();
        this.email = fazendeiro.getEmail();
        this.telefone = fazendeiro.getTelefone();
    }

    @JsonIgnore
    public Fazendeiro getFazendeiroModel() {
        Fazendeiro fazendeiro = new Fazendeiro();
        fazendeiro.setEmail(email);
        fazendeiro.setId(id);
        fazendeiro.setTelefone(telefone);
        return fazendeiro;
    }
}
