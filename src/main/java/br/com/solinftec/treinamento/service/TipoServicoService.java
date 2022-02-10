package br.com.solinftec.treinamento.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import br.com.solinftec.treinamento.configuration.TreinamentoDefaultException;
import br.com.solinftec.treinamento.dto.TipoServicoDto;
import br.com.solinftec.treinamento.model.TipoServico;
import br.com.solinftec.treinamento.repository.TipoServicoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TipoServicoService {
    private final TipoServicoRepository tipoServicoRepository;

    public TipoServicoDto getById(Long id) throws Exception {
        Optional<TipoServico> optTipoServico = tipoServicoRepository.findById(id);
        if (optTipoServico.isPresent()) {
            return new TipoServicoDto(optTipoServico.get());
        }
        throw new Exception("TIPO_SERVICO_NOT_FOUND");
    }

    public TipoServico getModelById(Long idTipo) throws TreinamentoDefaultException {
        return this.tipoServicoRepository.findById(
                idTipo)
                .orElseThrow(() -> new TreinamentoDefaultException("TIPO_SERVICE_NOT_FOUND"));
    }

    public TipoServicoDto save(@Valid TipoServicoDto tipoServicoDto) throws TreinamentoDefaultException {
        try {
            return new TipoServicoDto(tipoServicoRepository.save(tipoServicoDto.getModel()));
        } catch (Exception e) {
            throw new TreinamentoDefaultException(e.getMessage());
        }
    }

    public void delete(Long id) throws Exception {
        try {
            this.tipoServicoRepository.deleteById(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public TipoServicoDto update(@Valid TipoServicoDto tipoServicoDto) throws Exception {
        try {
            Optional<TipoServico> optTipoServico = tipoServicoRepository.findById(tipoServicoDto.getId());
            if (optTipoServico.isPresent()) {
                return new TipoServicoDto(this.tipoServicoRepository.save(tipoServicoDto.getModel()));
            } else {
                throw new Exception("TIPO_SERVICO_NOT_FOUND");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<TipoServicoDto> getAll() {
        return this.tipoServicoRepository.findAll().stream().map(TipoServicoDto::new).collect(Collectors.toList());

    }

}
