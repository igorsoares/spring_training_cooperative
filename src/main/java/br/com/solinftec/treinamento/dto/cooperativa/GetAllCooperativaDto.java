package br.com.solinftec.treinamento.dto.cooperativa;

import br.com.solinftec.treinamento.model.Cooperativa;
import lombok.Data;

@Data
public class GetAllCooperativaDto {
    private Long id;
    private String nome;
    private String email;
    private String logradouro;

    public GetAllCooperativaDto(Cooperativa cooperativa) {
        this.id = cooperativa.getId();
        this.nome = cooperativa.getNome();
        this.email = cooperativa.getEmail();
        this.logradouro = cooperativa.getLogradouro();
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
