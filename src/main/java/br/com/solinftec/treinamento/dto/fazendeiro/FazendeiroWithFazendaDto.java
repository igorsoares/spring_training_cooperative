package br.com.solinftec.treinamento.dto.fazendeiro;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.solinftec.treinamento.configuration.TreinamentoDefaultException;
import br.com.solinftec.treinamento.dto.fazenda.FazendaDto;
import br.com.solinftec.treinamento.model.Fazenda;
import br.com.solinftec.treinamento.model.Fazendeiro;
import lombok.Data;

@Data
public class FazendeiroWithFazendaDto {
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private List<FazendaDto> fazendas;

    public FazendeiroWithFazendaDto(Fazendeiro fazendeiro) {
        this.id = fazendeiro.getId();
        this.nome = fazendeiro.getNome();
        this.email = fazendeiro.getEmail();
        this.telefone = fazendeiro.getTelefone();
        this.fazendas = fazendeiro.getFazendas().stream().map(FazendaDto::new).collect(Collectors.toList());
    }

    @JsonIgnore
    public Fazendeiro getModel() throws TreinamentoDefaultException {
        Fazendeiro fazendeiro = new Fazendeiro();
        fazendeiro.setEmail(email);

        List<Fazenda> fazenda = fazendas.stream().map(t -> {
            try {
                return t.getFazendaModel();
            } catch (TreinamentoDefaultException e) {
                e.printStackTrace();
            }
            return null;
        }).collect(Collectors.toList());
        fazendeiro.setFazendas(fazenda);

        fazendeiro.setNome(nome);
        fazendeiro.setTelefone(telefone);
        fazendeiro.setId(id);
        return fazendeiro;
    }
}