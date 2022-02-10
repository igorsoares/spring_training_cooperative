package br.com.solinftec.treinamento.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.solinftec.treinamento.configuration.TreinamentoDefaultException;
import br.com.solinftec.treinamento.dto.monitoramento.MonitoramentoGetDto;
import br.com.solinftec.treinamento.dto.monitoramento.MonitoramentoIdDto;
import br.com.solinftec.treinamento.model.Equipamento;
import br.com.solinftec.treinamento.model.Monitoramento;
import br.com.solinftec.treinamento.repository.MonitoramentoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MonitoramentoService {
    private final MonitoramentoRepository monitoramentoRepository;
    private final EquipamentoService equipamentoService;
    private final OrdemServicoService ordemService;
    private static final String MSG_NOT_FOUND = "MONITORAMENTO_NOT_FOUND";

    private Monitoramento getModel(MonitoramentoIdDto monitoramento) throws TreinamentoDefaultException {
        Monitoramento monitoramentoModel = new Monitoramento();
        monitoramentoModel.setData_hora(monitoramento.getData_hora());
        monitoramentoModel.setId(monitoramento.getId());

        monitoramentoModel.setEquipamento(equipamentoService.getModelById(monitoramento.getEquipamento()));
        monitoramentoModel.setOrdemServico(ordemService.getModelById(monitoramento.getOrdemServico()));

        monitoramentoModel.setLatitude(monitoramento.getLatitude());
        monitoramentoModel.setLongitude(monitoramento.getLongitude());
        return monitoramentoModel;
    }

    public List<Monitoramento> getAll() {
        return monitoramentoRepository.findAll();
    }

    public MonitoramentoGetDto saveMonitoramento(MonitoramentoIdDto monitoramento) throws TreinamentoDefaultException {
        try {
            Monitoramento monitoramentoModel = this.getModel(monitoramento);
            Monitoramento monitoramentoSalvo = this.monitoramentoRepository.save(monitoramentoModel);
            // Salvar a posição do equipamento
            Equipamento equipamentoDoMonitoramento = monitoramentoSalvo.getEquipamento();
            equipamentoDoMonitoramento.setLatitude(monitoramentoSalvo.getLatitude());
            equipamentoDoMonitoramento.setLongitude(monitoramentoSalvo.getLongitude());

            equipamentoService.update(equipamentoDoMonitoramento); // Desafio 1.

            return new MonitoramentoGetDto(monitoramentoSalvo);
        } catch (Exception e) {
            throw new TreinamentoDefaultException(e.getMessage());
        }
    }

    public MonitoramentoGetDto getById(Long idMonitoramento) throws TreinamentoDefaultException {
        Monitoramento monitoramentoModel = this.monitoramentoRepository.findById(idMonitoramento)
                .orElseThrow(() -> new TreinamentoDefaultException(MSG_NOT_FOUND));
        return new MonitoramentoGetDto(monitoramentoModel);
    }

    public void delete(Long idMonitoramento) throws TreinamentoDefaultException {
        Optional<Monitoramento> optMonitoramento = this.monitoramentoRepository.findById(idMonitoramento);
        if (optMonitoramento.isPresent())
            this.monitoramentoRepository.deleteById(idMonitoramento);
        throw new TreinamentoDefaultException(MSG_NOT_FOUND);
    }

}
