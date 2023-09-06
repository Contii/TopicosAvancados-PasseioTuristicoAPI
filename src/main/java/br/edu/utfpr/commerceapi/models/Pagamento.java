package br.edu.utfpr.commerceapi.models;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
//importa todos os lombok
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//Lombok
@Getter
@Setter
@AllArgsConstructor
@ToString
// ou somente @Data

//jakarta - JPA
@Entity
@Table(name = "TB_Pagamento") // caso nao inserir ele coloca o nome da tabela
public class Pagamento extends BaseEntity {
   
    @Column(name = "clienteId", length = 140, nullable = false, unique = true)
    private UUID clienteId;

    @Column(name = "formaPagamento", length = 20, nullable = false, unique = true)
    private String formaPagamento;

    @Column(name = "valor")
    private Float valor;

    public Pagamento() {
    }
}