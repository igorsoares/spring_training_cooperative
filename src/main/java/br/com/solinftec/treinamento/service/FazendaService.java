package br.com.solinftec.treinamento.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.com.solinftec.treinamento.configuration.TreinamentoDefaultException;
import br.com.solinftec.treinamento.dto.fazenda.FazendaWithFazendeiroDto;
import br.com.solinftec.treinamento.model.Fazenda;
import br.com.solinftec.treinamento.model.Fazendeiro;
import br.com.solinftec.treinamento.repository.FazendaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FazendaService {

    private final FazendaRepository fazendaRepository;

    private final FazendeiroService fazendeiroService;

    private static final Logger logger = LoggerFactory.getLogger(Fazenda.class);

    public FazendaWithFazendeiroDto getFazendaById(Long id) throws Exception {
        Optional<Fazenda> fazendaOpt = this.fazendaRepository.findById(id);
        if (fazendaOpt.isPresent()) {
            return new FazendaWithFazendeiroDto(fazendaOpt.get());
        } else {
            throw new Exception("FAZENDA_NOT_FOUND");
        }
    }

    public Fazenda getModelById(Long idFazenda) throws TreinamentoDefaultException {
        return this.fazendaRepository.findById(
                idFazenda)
                .orElseThrow(() -> new TreinamentoDefaultException("FAZENDA_NOT_FOUND"));
    }

    public FazendaWithFazendeiroDto saveFazenda(FazendaWithFazendeiroDto fazendaDto) throws Exception {
        try {
            return new FazendaWithFazendeiroDto(this.fazendaRepository.save(fazendaDto.getFazendaModel()));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

    public void deleteFazenda(Long idFazenda) throws Exception {
        Optional<Fazenda> fazenda = this.fazendaRepository.findById(idFazenda);
        if (fazenda.isPresent()) {
            this.fazendaRepository.delete(fazenda.get());
        } else {
            throw new Exception("FAZENDA_NOT_FOUND");
        }
    }

    public List<FazendaWithFazendeiroDto> getAll() throws Exception {
        try {
            return this.fazendaRepository.findAll().stream().map(FazendaWithFazendeiroDto::new)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

    private Fazenda buscaFazendeiro(Long idFazendeiro, Fazenda fazenda) throws Exception {
        Optional<Fazendeiro> fazendeiro = Optional
                .ofNullable(this.fazendeiroService.getFazendeiroById(idFazendeiro));
        if (fazendeiro.isPresent()) {
            fazenda.setFazendeiro(fazendeiro.get());
            return fazenda;
        } else
            throw new Exception("FAZENDEIRO_NOT_FOUND");

    }

    public FazendaWithFazendeiroDto updateFazenda(FazendaWithFazendeiroDto fazendaDto) throws Exception {
        Optional<Fazenda> optFazenda = this.fazendaRepository.findById(fazendaDto.getId());
        if (optFazenda.isPresent()) {
            return new FazendaWithFazendeiroDto(this.fazendaRepository.save(fazendaDto.getFazendaModel()));
        }
        throw new Exception("FAZENDA_NOT_FOUND");

    }

}
