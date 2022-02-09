package br.com.solinftec.treinamento.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "FAZENDEIRO")
@NoArgsConstructor
@Data
public class Fazendeiro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "NOME")
    private String nome;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "TELEFONE")
    private String telefone;

    @OneToMany(mappedBy = "fazendeiro")
    @JsonBackReference
    private List<Fazenda> fazendas;

    public Fazendeiro(Long id) {
        this.id = id;
    }

}
