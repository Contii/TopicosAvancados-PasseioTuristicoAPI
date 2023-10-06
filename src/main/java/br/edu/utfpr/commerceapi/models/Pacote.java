package br.edu.utfpr.commerceapi.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Pacote extends BaseEntity {
    private String nome;
    private String descricao;
    private float valor;
    private int duracaoEmDias;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private int vagasDisponiveis;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    private List<Passeio> passeios = new ArrayList<>();
}