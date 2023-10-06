package br.edu.utfpr.commerceapi.dto;
import jakarta.validation.constraints.NotBlank;
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
public class PagamentoDTO {
    @NotBlank(message = "campo formaPagamento obrigatório.")
    private String formaPagamento;
}