package br.com.solinftec.treinamento.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import br.com.solinftec.treinamento.configuration.TreinamentoDefaultException;
import br.com.solinftec.treinamento.dto.equipamento.EquipamentoWithTipoDto;
import br.com.solinftec.treinamento.dto.equipamento.EquipamentoWithTipoIdDto;
import br.com.solinftec.treinamento.model.Equipamento;
import br.com.solinftec.treinamento.model.Monitoramento;
import br.com.solinftec.treinamento.repository.EquipamentoRepository;
import br.com.solinftec.treinamento.repository.MonitoramentoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
@Lazy
public class EquipamentoService {

    private final EquipamentoRepository repository;

    private final MonitoramentoRepository monitoramentoRepository;
    private final TipoEquipamentoService tipoEquipamentoService;
    private static final String MSG_NOT_FOUND = "EQUIPAMENTO_NOT_FOUND";
    private static final String MSG_ALREADY_EXISTS = "EQUIPAMENTO_ALREADY_EXISTS";

    public List<Equipamento> getAll() {
        return repository.findAll();
    }

    public boolean equipamentoAtivo(Long idEquipamento) throws TreinamentoDefaultException {
        Optional<Monitoramento> monitoramentoOpt = monitoramentoRepository
                .findMonitoramentoByEquipamentoId(idEquipamento);
        if (monitoramentoOpt.isPresent()) {
            Monitoramento model = monitoramentoOpt.get();
            LocalDateTime nowSubtracted = LocalDateTime.now().minusMinutes(15);
            Integer resultCompare = model.getData_hora().compareTo(nowSubtracted);
            if (resultCompare > 0)
                return true;
        }
        return false;
    }

    public EquipamentoWithTipoDto getById(Long id) throws TreinamentoDefaultException {
        log.info("Obtendo o equipamentos.");
        Equipamento equipamentoModel = repository.findById(id)
                .orElseThrow(() -> new TreinamentoDefaultException(MSG_NOT_FOUND));
        equipamentoModel.setAtivo(this.equipamentoAtivo(id));
        this.update(equipamentoModel);

        return new EquipamentoWithTipoDto(equipamentoModel);
    }

    public Equipamento getModelById(Long idEquipamento) throws TreinamentoDefaultException {
        Optional<Equipamento> optEquipamento = this.repository.findById(idEquipamento);
        if (optEquipamento.isPresent())
            return optEquipamento.get();
        throw new TreinamentoDefaultException(MSG_NOT_FOUND);
    }

    public Equipamento save(EquipamentoWithTipoIdDto equipamento) throws Exception {
        try {
            Optional<Equipamento> tipoOptional = Optional
                    .ofNullable(repository.findByDescricao(equipamento.getDescricao()));
            if (tipoOptional.isPresent())
                throw new Exception(MSG_ALREADY_EXISTS);
            Equipamento equipamentoModel = equipamento.getModel();
            // Get tipoEquipamento
            equipamentoModel.setTipoEquipamento(this.tipoEquipamentoService.getById(equipamento.getTipoEquipamento()));
            return repository.save(equipamentoModel);

        } catch (Exception e) {
            log.error(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    public void delete(Long idequipamento) throws Exception {
        repository.findById(idequipamento).ifPresent(obj -> {
            repository.delete(obj);
            return;
        });
        throw new Exception("ASLD??KASLD");

    }

    public Equipamento update(EquipamentoWithTipoIdDto equipamento) throws TreinamentoDefaultException {
        Optional<Equipamento> optEquipamento = repository.findById(equipamento.getId());
        if (optEquipamento.isPresent()) {
            Equipamento equipamentoModel = equipamento.getModel();
            return repository.save(equipamentoModel);
        } else
            throw new TreinamentoDefaultException(MSG_NOT_FOUND);
    }

    public Equipamento update(Equipamento equipamento) throws TreinamentoDefaultException {
        // Essa fun????o de polimorfismo ?? usada para atualizar o equipamento a partir de
        // uma inser????o de monitoramento.
        Optional<Equipamento> optEquipamento = repository.findById(equipamento.getId());
        if (optEquipamento.isPresent()) {
            return repository.save(equipamento);
        } else
            throw new TreinamentoDefaultException(MSG_NOT_FOUND);
    }

}
