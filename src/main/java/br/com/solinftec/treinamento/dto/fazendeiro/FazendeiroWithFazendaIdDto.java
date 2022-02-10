package br.com.solinftec.treinamento.dto.fazendeiro;

import java.util.List;

import lombok.Data;

// ESSE DTO É USADO PARA SALVAR UM FAZENDEIRO COM ID DA FAZENDA AO INVPES DO OBJETO

@Data
public class FazendeiroWithFazendaIdDto {
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private List<Long> fazendas;

}
