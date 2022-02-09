package br.com.solinftec.treinamento.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.solinftec.treinamento.model.Monitoramento;
import br.com.solinftec.treinamento.repository.MonitoramentoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MonitoramentoService {
    private final MonitoramentoRepository monitoramentoRepository;

    public List<Monitoramento> getAll() {
        return monitoramentoRepository.findAll();
    }

}
