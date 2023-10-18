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
@Table(name = "TB_Passeio")
public class Passeio extends BaseEntity {
   
    @Column(name = "name", length = 140, nullable = false)
    private String nome;

    @Column(name = "descricao", length = 500, nullable = false)
    private String descricao;

    @Column(name = "valor", nullable = false)
    private float valor;

    @Column(name = "duracaoEmHoras", nullable = false)
    private int duracaoEmHoras;

    @Column(name = "vagasDisponiveis", nullable = false)
    private int vagasDisponiveis;

    @Column(name = "classificacao", nullable = false)
    private int classificacao;

    @ManyToOne
    private Pacote pacote;
}