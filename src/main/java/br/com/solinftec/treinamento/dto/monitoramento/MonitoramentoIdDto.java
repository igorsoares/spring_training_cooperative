package br.com.solinftec.treinamento.dto.monitoramento;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import br.com.solinftec.treinamento.model.Monitoramento;
import lombok.Data;

// THIS DTO IS A MONITORAMENTO BUT WITH ID INSTEAD OBJECTS
// USED ON: SAVING A MONITORAMENTO
@Data
public class MonitoramentoIdDto {
    private Long id;
    @NotNull(message = "LATITUDE_CANNOT_BE_NULL")
    private Double latitude;
    @NotNull(message = "LONGITUDE_CANNOT_BE_NULL")
    private Double longitude;
    @NotNull(message = "DATA_HORA_CANNOT_BE_NULL")
    private LocalDateTime data_hora;
    @NotNull(message = "EQUIPAMENTO_CANNOT_BE_NULL")
    private Long equipamento;
    @NotNull(message = "ORDEM_SERVICO_CANNOT_BE_NULL")
    private Long ordemServico;

    public MonitoramentoIdDto() {
    }

    public MonitoramentoIdDto(Monitoramento monitoramentoModel) {

    }
}
