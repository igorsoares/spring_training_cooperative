package br.com.solinftec.treinamento.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.solinftec.treinamento.dto.equipamento.SaveEquipamentoDto;
import br.com.solinftec.treinamento.model.Equipamento;
import br.com.solinftec.treinamento.repository.EquipamentoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor

public class EquipamentoService {

    private final EquipamentoRepository repository;
    private final TipoEquipamentoService tipoEquipamentoService;

    public List<Equipamento> getAll() {
        // log.info("Obtendo todos os equipamentos.");
        return repository.findAll();
    }

    public Equipamento getById(Long id) throws Exception {
        log.info("Obtendo o equipamentos.");
        Optional<Equipamento> equipamentoOptional = Optional
                .ofNullable(repository.findById(id).get());
        if (equipamentoOptional.isPresent())
            return equipamentoOptional.get();
        throw new Exception("EQUIPAMENTO_NOT_FOUND");
    }

    public Equipamento save(SaveEquipamentoDto equipamento) throws Exception {
        try {
            Optional<Equipamento> tipoOptional = Optional
                    .ofNullable(repository.findByDescricao(equipamento.getDescricao()));
            if (tipoOptional.isPresent())
                throw new Exception("EQUIPAMENTO_EXISTENTE");
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
        throw new Exception("ASLDÇKASLD");

    }

    public Equipamento update(SaveEquipamentoDto equipamento) throws Exception {
        Optional<Equipamento> optEquipamento = Optional
                .ofNullable(repository.findById(equipamento.getId()).get());
        if (optEquipamento.isPresent()) {
            Equipamento equipamentoModel = equipamento.getModel();
            // Get tipoEquipamento
            equipamentoModel.setTipoEquipamento(this.tipoEquipamentoService.getById(equipamento.getTipoEquipamento()));
            return repository.save(equipamentoModel);
        } else
            throw new Exception("EQUIPAMENTO_NOT_FOUND");
    }

}
