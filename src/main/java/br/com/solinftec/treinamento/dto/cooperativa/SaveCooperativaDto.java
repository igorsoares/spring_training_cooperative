package br.com.solinftec.treinamento.dto.cooperativa;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import br.com.solinftec.treinamento.model.Cooperativa;
import br.com.solinftec.treinamento.model.Fazendeiro;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class SaveCooperativaDto {
    private Long id;
    @NotNull(message = "NOME_CANNOT_BE_NULL")
    private String nome;
    @Email(message = "EMAIL_CANNOT_BE_NULL")
    private String email;
    @NotNull(message = "LOGRADOURO_CANNOT_BE_NULL")
    private String logradouro;
    private List<Long> fazendeiros;

    public Cooperativa pegarModel() {
        Cooperativa coop = new Cooperativa();
        coop.setAtivo(true);
        coop.setNome(this.nome);
        coop.setEmail(this.email);
        coop.setId(null);
        coop.setLogradouro(this.logradouro);
        log.info(">>>>>>>> FAZENDEIROS: {}", this.fazendeiros);
        coop.setFazendeiros(this.fazendeiros.stream().map(Fazendeiro::new).collect(Collectors.toList()));
        return coop;

    }
}
