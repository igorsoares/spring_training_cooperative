package br.com.solinftec.treinamento.dto.fazendeiro;

import java.util.List;
import java.util.stream.Collectors;

import br.com.solinftec.treinamento.dto.fazenda.FazendaDto;
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
}