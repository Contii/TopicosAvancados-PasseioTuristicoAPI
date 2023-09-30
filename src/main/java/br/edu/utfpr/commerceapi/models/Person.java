package br.edu.utfpr.commerceapi.models;
import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "TB_Person") // caso nao inserir ele coloca o nome da classe
public class Person extends BaseEntity {
   
    @Column(name = "name", length = 140, nullable = false)
    private String nome;

    @Column(name = "email", length = 100, nullable = false, unique = true)
    private String email;

    @Column(name = "password", length = 50, nullable = false)
    private String senha;

    @Column(name = "phone", length = 20, nullable = true)
    private String telefone;

    @Column(name = "birth", nullable = true)
    private LocalDateTime nascimento;

    @Column(name = "perfil", length = 20, nullable = false) // Cliente ou Agencia
    private String perfil;
}