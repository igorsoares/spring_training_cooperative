package br.com.solinftec.Treinamento.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.com.solinftec.Treinamento.dto.SaveFazendaDto;
import br.com.solinftec.Treinamento.model.Fazenda;
import br.com.solinftec.Treinamento.model.Fazendeiro;
import br.com.solinftec.Treinamento.repository.FazendaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FazendaService {

    private final FazendaRepository fazendaRepository;

    private final FazendeiroService fazendeiroService;

    private static final Logger logger = LoggerFactory.getLogger(Fazenda.class);

    public Fazenda getFazendaById(Long id) throws Exception {
        Optional<Fazenda> fazendaOpt = this.fazendaRepository.findById(id);
        if (fazendaOpt.isPresent()) {
            logger.info("Fazenda OPT : {}", fazendaOpt.get().toString());
            return fazendaOpt.get();
        } else {
            throw new Exception("FAZENDA_NOT_FOUND");
        }
    }

    public SaveFazendaDto saveFazenda(SaveFazendaDto fazendaDto) throws Exception {
        try {
            Fazenda fazendaModel = fazendaDto.getFazendaModel();
            Optional<Fazendeiro> fazendeiro = Optional
                    .ofNullable(this.fazendeiroService.getFazendeiroById(fazendaDto.getId_fazendeiro()));
            if (fazendeiro.isPresent())
                fazendaModel.setFazendeiro(fazendeiro.get());
            else
                throw new Exception("FAZENDEIRO_NOT_FOUND");

            fazendaModel = this.fazendaRepository.save(fazendaModel);
            fazendaDto.setId(fazendaModel.getId());
            // fazendaDto.setId_fazendeiro(fazendaModel.getFazendeiro().getId());
            return fazendaDto;
        } catch (Exception e) {
            logger.error("Erro ao adicionar fazenda : {}", e.getMessage());
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

    public List<Fazenda> getAll() throws Exception {
        try {
            return this.fazendaRepository.findAll();
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

    public Fazenda updateFazenda(SaveFazendaDto fazendaDto) throws Exception {
        Optional<Fazenda> optFazenda = this.fazendaRepository.findById(fazendaDto.getId());
        if (optFazenda.isPresent()) {
            Fazenda fazenda = fazendaDto.getFazendaModel();
            fazenda = this.buscaFazendeiro(fazendaDto.getId(), fazenda);

            return this.fazendaRepository.save(fazenda);
        }
        throw new Exception("FAZENDA_NOT_FOUND");

    }

}
