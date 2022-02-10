package br.com.solinftec.treinamento.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.solinftec.treinamento.configuration.TreinamentoDefaultException;
import br.com.solinftec.treinamento.dto.cooperativa.GetAllCooperativaDto;
import br.com.solinftec.treinamento.dto.cooperativa.SaveCooperativaDto;
import br.com.solinftec.treinamento.model.Cooperativa;
import br.com.solinftec.treinamento.model.Fazendeiro;
import br.com.solinftec.treinamento.repository.CooperativaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor // pega todas variaveis como FINAL e gerar
// construtor para elas
public class CooperativaService {

    private final CooperativaRepository cooperativaRepository;

    public GetAllCooperativaDto getById(Long idCooperativa) throws TreinamentoDefaultException {
        Cooperativa coopModel = this.cooperativaRepository.findById(idCooperativa)
                .orElseThrow(() -> new TreinamentoDefaultException("COOPERATIVA_NOT_FOUND"));
        return new GetAllCooperativaDto(coopModel);
    }

    public Cooperativa getModelById(Long idCooperativa) throws TreinamentoDefaultException {
        return this.cooperativaRepository.findById(idCooperativa)
                .orElseThrow(() -> new TreinamentoDefaultException("COOPERATIVA_NOT_FOUND"));

    }

    public List<Fazendeiro> getFazendeirosDaCooperativa(Long idCooperativa) {
        Optional<Cooperativa> optCoop = this.cooperativaRepository.findById(idCooperativa);
        if (optCoop.isPresent()) {
            return optCoop.get().getFazendeiros();
        }
        return new ArrayList<>();
    }

    public List<GetAllCooperativaDto> getAll() {
        return this.cooperativaRepository.findAll().stream().map(coop -> new GetAllCooperativaDto(coop))
                .collect(Collectors.toList());
    }

    public SaveCooperativaDto save(SaveCooperativaDto saveCooperativa) {
        Cooperativa coop = saveCooperativa.pegarModel();
        coop.setId(saveCooperativa.getId());
        this.cooperativaRepository.save(coop);
        return saveCooperativa;
    }

    public SaveCooperativaDto update(SaveCooperativaDto saveCooperativa) throws Exception {
        Optional<Cooperativa> opt = this.cooperativaRepository.findById(saveCooperativa.getId());
        if (opt.isPresent()) {
            this.cooperativaRepository.save(saveCooperativa.pegarModel());
            return saveCooperativa;
        } else
            throw new Exception("COOPERATIVA_NOT_FOUND");

    }

    public void deletar(Long idCoop) throws Exception {
        Optional<Cooperativa> opt = this.cooperativaRepository.findById(idCoop);
        if (opt.isPresent()) {
            this.cooperativaRepository.deleteById(idCoop);
        } else
            throw new Exception("COOPERATIVA_NOT_FOUND");

    }

}
