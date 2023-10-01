package br.edu.utfpr.commerceapi.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//Lombok
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
// ou somente @Data

// jakarta - JPA
@Entity
@Table(name = "TB_Pacote") // caso nao inserir ele coloca o nome da tabela
public class Pacote extends BaseEntity {

    @Column(name = "name", length = 140, nullable = false, unique = true)
    private String nome;

    @Column(name = "descricao", length = 100, nullable = false)
    private String descricao;

    @Column(name = "valor", nullable = false)
    private float valor;

    @Column(name = "duracaoEmDias", nullable = false)
    private int duracaoEmDias;

    @Column(name = "dataInicio", nullable = false)
    private LocalDate dataInicio;

    @Column(name = "dataFim", nullable = false)
    private LocalDate dataFim;

    @Column(name = "vagasDisponiveis", nullable = false)
    private int vagasDisponiveis;

    //quais passeios este pacote inclui
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pacote") // pacote é o atributo na classe Passeio
    private List<Passeio> passeios = new ArrayList<>();
}