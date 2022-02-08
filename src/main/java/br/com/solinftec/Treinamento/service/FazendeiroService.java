package br.com.solinftec.Treinamento.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.solinftec.Treinamento.model.Fazendeiro;
import br.com.solinftec.Treinamento.repository.FazendeiroRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FazendeiroService {

    private final FazendeiroRepository fazendeiroRepository;

    public Fazendeiro getFazendeiroById(Long id) throws Exception {
        Optional<Fazendeiro> fazendeiro = this.fazendeiroRepository.findById(id);
        if (fazendeiro.isPresent()) {
            return fazendeiro.get();
        } else {
            throw new Exception("FAZENDEIRO_NOT_FOUND");
        }
    }

}
