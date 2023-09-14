package br.edu.utfpr.commerceapi.models;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
//importa todos os lombok
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

//jakarta - JPA
@Entity
@Table(name = "TB_Pagamento") // caso nao inserir ele coloca o nome da tabela
public class Pagamento extends BaseEntity {
   
    @Column(name = "valor", nullable = false)
    private float valor;

    @Column(name = "formaPagamento", length = 20, nullable = false)
    private String formaPagamento;

    @ManyToOne
    @JoinColumn(name = "pacote_id")
    private Pacote pacote;

    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    private Person pessoa;

}