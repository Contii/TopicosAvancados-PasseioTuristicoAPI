package br.edu.utfpr.commerceapi.models;
import java.time.LocalDateTime;
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
@Table(name = "TB_Person") // caso nao inserir ele coloca o nome da tabela
public class Person extends BaseEntity {
   
    @Column(name = "name", length = 140, nullable = false)
    private String nome;

    @Column(name = "perfil", length = 20, nullable = false) // Cliente ou Agencia De Viagens
    private String perfil;

    @Column(name = "email", length = 100, nullable = false, unique = true)
    private String email;

    @Column(name = "birth", nullable = true)
    private LocalDateTime nascimento;
 

    public Person() {
    }
}
