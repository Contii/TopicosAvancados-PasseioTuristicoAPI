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
@Table(name = "TB_Pagamento")
public class Pagamento extends BaseEntity {

    @Column(name = "formaPagamento", length = 20, nullable = false)
    private String formaPagamento;

    @ManyToOne
    private Pacote pacote;
    
    @ManyToOne
    private Person pessoa;
}