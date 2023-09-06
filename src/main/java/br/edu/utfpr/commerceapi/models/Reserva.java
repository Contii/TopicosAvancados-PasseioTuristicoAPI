package br.edu.utfpr.commerceapi.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Setter
@Getter
public class Reserva extends BaseEntity {
    
    @Column(name = "name", length = 140, nullable = false)
    private String nome;

    //dia do passeio/reserva
    private LocalDateTime data;

    //pacote que possui este passeio/reserva
    @ManyToOne
    @JoinColumn(name = "pacote_id") //pacote_id é referência à chave estrangeira na tabela
    private Pacote pacote;

}
