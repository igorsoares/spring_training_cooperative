package br.com.solinftec.Treinamento.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.solinftec.Treinamento.model.Cooperativa;
import br.com.solinftec.Treinamento.model.Fazendeiro;

public class SaveCooperativaDto {
    private Long id;
    private String nome;
    private String email;
    private List<Long> fazendeiros;

    public Cooperativa pegarModel() {
        Cooperativa coop = new Cooperativa();
        coop.setAtivo(true);
        coop.setNome(this.nome);
        coop.setEmail(this.email);
        coop.setId(null);
        coop.setFazendeiros(this.fazendeiros.stream().map(Fazendeiro::new).collect(Collectors.toList()));
        return coop;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
