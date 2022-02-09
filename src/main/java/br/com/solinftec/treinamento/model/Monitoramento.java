package br.com.solinftec.treinamento.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "MONITORAMENTO")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Monitoramento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "LATITUDE")
    private Double latitude;
    @Column(name = "LONGITUDE")
    private Double longitude;
    @Column(name = "DATA_HORA")
    private LocalDateTime data_hora;

    @ManyToOne
    @JoinColumn(name = "ID_EQUIPAMENTO")
    private Equipamento equipamento;

    @ManyToOne
    @JoinColumn(name = "ID_ORDEM_SERVICO")
    private OrdemServico ordemServico;

}
