package br.edu.utfpr.commerceapi.models;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "TB_Reserva") // caso nao inserir ele coloca o nome da classe
public class Reserva extends BaseEntity {
    
    //dia do passeio
    @Column(name = "dataReserva", nullable = false)
    private LocalDate dataReserva;

    @ManyToOne
    @JoinColumn(name = "pagamento_id") //passeio_id é a chave estrangeira importada da tabela Passeio
    private Pagamento pagamento;

    @ManyToOne
    @JoinColumn(name = "passeio_id") //passeio_id é a chave estrangeira importada da tabela Passeio
    private Passeio passeio;
}