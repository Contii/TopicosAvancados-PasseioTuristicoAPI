package br.edu.utfpr.commerceapi.dto;
import jakarta.validation.constraints.NotBlank;
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

public class PagamentoDTO {
    @NotBlank(message = "campo formaPagamento obrigatório.")
    private String formaPagamento;
}
