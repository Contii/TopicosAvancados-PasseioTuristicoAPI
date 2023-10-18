package br.edu.utfpr.commerceapi.models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Entity
@Table(name = "TB_Reserva")
public class Reserva extends BaseEntity {
    
    @Column(name = "dataReserva", nullable = false)
    private LocalDate dataReserva;

    @ManyToOne
    private Pagamento pagamento;

    @ManyToOne
    private Passeio passeio;
}