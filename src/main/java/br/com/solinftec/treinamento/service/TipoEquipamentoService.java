package br.com.solinftec.treinamento.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.solinftec.treinamento.dto.equipamento.SaveTipoEquipamentoDto;
import br.com.solinftec.treinamento.model.TipoEquipamento;
import br.com.solinftec.treinamento.repository.TipoEquipamentoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class TipoEquipamentoService {

    private final TipoEquipamentoRepository repository;

    public List<TipoEquipamento> getAll() {
        log.info("Obtendo todos os tipos de equipamentos.");
        return repository.findAll();
    }

    public TipoEquipamento getById(Long id) throws Exception {
        Optional<TipoEquipamento> tipoOptional = Optional
                .ofNullable(repository.findById(id).get());
        if (tipoOptional.isPresent())
            return tipoOptional.get();
        throw new Exception("TIPO_EQUIPAMENTO_NOT_FOUND");
    }

    public TipoEquipamento save(SaveTipoEquipamentoDto tipoEquipamento) throws Exception {
        Optional<TipoEquipamento> tipoOptional = Optional
                .ofNullable(repository.findByDescricao(tipoEquipamento.getDescricao()));
        if (tipoOptional.isPresent())
            throw new Exception("O equipamento já existe.");
        return repository.save(tipoEquipamento.getModel());
    }

    public void delete(Long idTipoEquipamento) throws Exception {
        Optional<TipoEquipamento> tipoOptional = Optional
                .ofNullable(repository.findById(idTipoEquipamento).get());
        if (tipoOptional.isPresent())
            repository.delete(tipoOptional.get());
        else
            throw new Exception("TIPO_EQUIPAMENTO_NOT_FOUND");
    }

    public TipoEquipamento update(SaveTipoEquipamentoDto tipoEquipamento) throws Exception {
        Optional<TipoEquipamento> tipoOptional = Optional
                .ofNullable(repository.findById(tipoEquipamento.getId()).get());
        if (tipoOptional.isPresent())
            return repository.save(tipoEquipamento.getModel());
        else
            throw new Exception("TIPO_EQUIPAMENTO_NOT_FOUND");
    }

}
