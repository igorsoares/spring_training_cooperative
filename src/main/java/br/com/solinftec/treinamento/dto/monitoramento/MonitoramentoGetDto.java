package br.com.solinftec.treinamento.dto.monitoramento;

import java.time.LocalDateTime;

import br.com.solinftec.treinamento.model.Equipamento;
import br.com.solinftec.treinamento.model.Monitoramento;
import br.com.solinftec.treinamento.model.OrdemServicoModel;
import lombok.Data;

// THIS DTO IS USED FOR GET ENDPOINTS BY ID 

@Data
public class MonitoramentoGetDto {

    private Long id;
    private Double latitude;
    private Double longitude;
    private LocalDateTime data_hora;
    private Equipamento equipamento;
    private OrdemServicoModel ordemServico;

    public MonitoramentoGetDto(Monitoramento monitoramentoModel) {
        this.id = monitoramentoModel.getId();
        this.latitude = monitoramentoModel.getLatitude();
        this.longitude = monitoramentoModel.getLongitude();
        this.data_hora = monitoramentoModel.getData_hora();
        this.equipamento = monitoramentoModel.getEquipamento();
        this.ordemServico = monitoramentoModel.getOrdemServico();
    }

}
