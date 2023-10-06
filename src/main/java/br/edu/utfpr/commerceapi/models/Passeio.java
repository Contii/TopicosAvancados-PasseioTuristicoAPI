package br.edu.utfpr.commerceapi.models;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

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
@Table
public class Passeio extends BaseEntity {
   
    @Column(length = 140, nullable = false)
    private String nome;

    @Column(length = 500, nullable = false)
    private String descricao;

    @Column(nullable = false)
    private float valor;

    @Column(nullable = false)
    private int duracaoEmHoras;

    @Column(nullable = false)
    private int vagasDisponiveis;

    @Column(nullable = false)
    private int classificacao;

    @ManyToOne
    private Pacote pacote;
}