package br.com.solinftec.treinamento.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.solinftec.treinamento.configuration.TreinamentoDefaultException;
import br.com.solinftec.treinamento.dto.ordemServico.OrdemCalculoCooperativaDiaDto;
import br.com.solinftec.treinamento.dto.ordemServico.OrdemServicoDto;
import br.com.solinftec.treinamento.model.Equipamento;
import br.com.solinftec.treinamento.model.OrdemServicoModel;
import br.com.solinftec.treinamento.repository.OrdemServicoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrdemServicoService {

    private final OrdemServicoRepository ordemServicoRepository;
    private final CooperativaService cooperativaService;
    private final EquipamentoService equipamentoService;
    private final FazendaService fazendaService;
    private final ProdutoService produtoService;
    private final TipoServicoService tipoService;
    private static final String MSG_NOT_FOUND = "ORDEM_NOT_FOUND";

    private OrdemServicoModel getModel(OrdemServicoDto ordem) throws TreinamentoDefaultException {
        OrdemServicoModel model = new OrdemServicoModel();
        model.setCooperativa(cooperativaService.getModelById(ordem.getCooperativa()));

        Equipamento equipamento = equipamentoService.getModelById(ordem.getEquipamento());
        log.info("Equipamento retornado : {}", equipamento);
        model.setEquipamento(equipamento);

        model.setFazenda(fazendaService.getModelById(ordem.getFazenda()));
        model.setProduto(produtoService.getModelById(ordem.getProduto()));
        model.setTipo_servico(tipoService.getModelById(ordem.getTipoServico()));
        model.setTotal_aplicacao(ordem.getTotal_aplicacao());
        model.setRate_aplicacao(ordem.getRate_aplicacao());
        model.setData_execucao(ordem.getData_execucao());
        model.setId(ordem.getId());
        return model;
    }

    public OrdemServicoModel getModelById(Long idOrdem) throws TreinamentoDefaultException {
        Optional<OrdemServicoModel> optOrdem = this.ordemServicoRepository.findById(idOrdem);
        if (optOrdem.isPresent())
            return optOrdem.get();
        throw new TreinamentoDefaultException(MSG_NOT_FOUND);
    }

    public OrdemServicoDto getById(Long idOrdem) throws TreinamentoDefaultException {
        log.info("Obtendo ordem por id {}", idOrdem);
        OrdemServicoModel ordemModel = ordemServicoRepository.findById(idOrdem).orElseThrow(
                () -> new TreinamentoDefaultException(MSG_NOT_FOUND));
        return new OrdemServicoDto(ordemModel);
    }

    public List<OrdemServicoDto> getAll() {
        log.info("Obtendo todas as ordens");
        return this.ordemServicoRepository.findAll()
                .stream().map(OrdemServicoDto::new).collect(Collectors.toList());
    }

    public OrdemServicoDto saveOrdem(OrdemServicoDto ordem) throws TreinamentoDefaultException {
        OrdemServicoModel model = this.getModel(ordem);
        model.setTotal_aplicacao(model.getFazenda().getArea() * model.getRate_aplicacao());
        return new OrdemServicoDto(this.ordemServicoRepository.save(model));
    }

    public void deleteOrdem(Long idOrdem) throws TreinamentoDefaultException {
        OrdemServicoModel ordemModel = ordemServicoRepository.findById(idOrdem)
                .orElseThrow(() -> new TreinamentoDefaultException(MSG_NOT_FOUND));
        this.ordemServicoRepository.delete(ordemModel);
    }

    public List<OrdemServicoDto> filterByDate(LocalDate dataExecucao) {
        Date date = Date.from(dataExecucao.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Optional<List<OrdemServicoModel>> optList = this.ordemServicoRepository.findByDate(date);
        if (optList.isPresent()) {
            return optList.get().stream().map(OrdemServicoDto::new).collect(Collectors.toList());
        }
        return new ArrayList<>();

    }

    public List<OrdemCalculoCooperativaDiaDto> calcCooperativaByDay(LocalDate dtExecucao) {
        Date date = Date.from(dtExecucao.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Optional<List<OrdemCalculoCooperativaDiaDto>> optList = this.ordemServicoRepository.calcCooperativaByDay(date);
        if (optList.isPresent()) {
            return optList.get();
        }
        return new ArrayList<>();
    }

}
