package br.com.solinftec.treinamento.dto.monitoramento;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class MonitoramentoFullDto {
    private Long id;
    private Double latitude;
    private Double longitude;
    private LocalDateTime data_hora;

    public MonitoramentoFullDto() {
    }

}
