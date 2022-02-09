package br.com.solinftec.Treinamento.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import br.com.solinftec.Treinamento.dto.GetAllCooperativaDto;
import br.com.solinftec.Treinamento.dto.SaveCooperativaDto;
import br.com.solinftec.Treinamento.model.Cooperativa;
import br.com.solinftec.Treinamento.model.Fazendeiro;
import br.com.solinftec.Treinamento.repository.CooperativaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor // pega todas variaveis como FINAL e gerar
// construtor para elas
public class CooperativaService {

    private final CooperativaRepository cooperativaRepository;

    // public CooperativaService(CooperativaRepository cooperativaRepository) {
    // this.cooperativaRepository = cooperativaRepository;
    // }

    @PostConstruct
    public void init() {
        System.out.println(cooperativaRepository);
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
