package br.com.solinftec.treinamento.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;

@Data

@Entity
@Table(name = "COOPERATIVA")
public class Cooperativa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "ATIVO")
    private Boolean ativo;

    @ManyToMany
    @JoinTable(name = "COOPERATIVA_FAZENDEIRO", joinColumns = {
            @JoinColumn(name = "ID_COOPERATIVA", referencedColumnName = "ID") }, inverseJoinColumns = {
                    @JoinColumn(name = "ID_FAZENDEIRO", referencedColumnName = "ID") })
    private List<Fazendeiro> fazendeiros;

    public Cooperativa(Long id) {
        this.id = id;
    }

    public Cooperativa() {
    }

}
