package br.edu.utfpr.commerceapi.models;
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
public class Pagamento extends BaseEntity {
    private String formaPagamento;

    @ManyToOne
    private Pacote pacote;
    
    @ManyToOne
    private Person pessoa;
}