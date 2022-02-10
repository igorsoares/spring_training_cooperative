package br.com.solinftec.treinamento.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ORDEM_SERVICO")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdemServicoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_COOPERATIVA")
    private Cooperativa cooperativa;

    @ManyToOne
    @JoinColumn(name = "ID_FAZENDA")
    private Fazenda fazenda;

    @ManyToOne
    @JoinColumn(name = "ID_EQUIPAMENTO")
    private Equipamento equipamento;

    @ManyToOne
    @JoinColumn(name = "ID_PRODUTO")
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "ID_TIPO_SERVICO")
    private TipoServico tipo_servico;

    @UpdateTimestamp
    private Date data_execucao;

    @Column(name = "RATE_APLICACAO")
    private Double rate_aplicacao;

    @Column(name = "TOTAL_APLICACAO")
    private Double total_aplicacao;

}
