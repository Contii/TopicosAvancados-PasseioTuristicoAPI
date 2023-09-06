package br.edu.utfpr.commerceapi.dto;
import java.time.LocalDateTime;

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
public class PersonDTO {
   
    private String nome;
    private String perfil;
    private String email;
    private LocalDateTime nascimento;

    //senha
    //telefone

}
