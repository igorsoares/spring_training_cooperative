package br.com.solinftec.treinamento.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.solinftec.treinamento.configuration.TreinamentoDefaultException;
import br.com.solinftec.treinamento.dto.fazendeiro.FazendeiroWithFazendaDto;
import br.com.solinftec.treinamento.dto.fazendeiro.FazendeiroWithFazendaIdDto;
import br.com.solinftec.treinamento.model.Fazenda;
import br.com.solinftec.treinamento.model.Fazendeiro;
import br.com.solinftec.treinamento.repository.FazendeiroRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FazendeiroService {

    private final FazendeiroRepository fazendeiroRepository;

    // @JsonBackReference
    // private final FazendaService fazendaService;

    public Fazendeiro getFazendeiroById(Long id) throws Exception {
        Optional<Fazendeiro> fazendeiro = this.fazendeiroRepository.findById(id);
        if (fazendeiro.isPresent()) {
            return fazendeiro.get();
        } else {
            throw new Exception("FAZENDEIRO_NOT_FOUND");
        }
    }

    public FazendeiroWithFazendaDto getFazendeiro(Long idFazendeiro) throws TreinamentoDefaultException {
        Fazendeiro fazendeiro = fazendeiroRepository.findById(idFazendeiro)
                .orElseThrow(() -> new TreinamentoDefaultException("FAZENDEIRO_NOT_FOUND"));
        return new FazendeiroWithFazendaDto(fazendeiro);
    }

    private Fazendeiro getModel(FazendeiroWithFazendaIdDto fazendeiroIdDto, List<Fazenda> fazendas) {
        Fazendeiro fazendeiro = new Fazendeiro();
        fazendeiro.setEmail(fazendeiroIdDto.getEmail());
        fazendeiro.setId(fazendeiroIdDto.getId());
        fazendeiro.setNome(fazendeiroIdDto.getNome());
        fazendeiro.setTelefone(fazendeiroIdDto.getTelefone());
        fazendeiro.setFazendas(fazendas);
        return fazendeiro;
    }

    // public FazendeiroWithFazendaDto saveFazendeiro(
    // FazendeiroWithFazendaIdDto fazendeiro)
    // throws TreinamentoDefaultException {
    // try {
    // // Obter todas as fazendas com o respective ID

    // List<Fazenda> fazendas = fazendeiro.getFazendas().stream()
    // .map(id -> {
    // try {
    // return fazendaService.getFazendaById(id).getFazendaModel();
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    // return null;
    // })
    // .collect(Collectors.toList());

    // Fazendeiro fazendeiroResult = this.getModel(fazendeiro, fazendas);

    // return new FazendeiroWithFazendaDto(fazendeiroResult);
    // } catch (Exception e) {
    // throw new TreinamentoDefaultException(e.getMessage());
    // }
    // }

}
