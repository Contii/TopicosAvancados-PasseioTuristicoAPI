package br.edu.utfpr.commerceapi.dto;
import java.time.LocalDate;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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

public class PacoteDTO {
    @NotBlank(message = "campo nome obrigatório.")
    @Size(min=3, max=100, message = "Deve conter entre 3 e 100 caracteres.")
    private String nome;

    @NotBlank(message = "campo descricao obrigatório.")
    @Size(min=20, max=500, message = "Deve conter entre 20 e 500 caracteres.")
    private String descricao;

    @NotBlank(message = "campo valor obrigatório.")
    @Min(value = 50, message = "Valor mínimo 50.")
    @Max(value = 10000, message = "Valor máximo 10000.")
    private float valor;

    @NotBlank(message = "campo duracaoEmDias obrigatório.")
    @Min(value = 1, message = "Duração em dias mínima 1.")
    @Max(value = 7, message = "Duração em dias máxima 7.")
    private int duracaoEmDias;

    @NotBlank(message = "campo dataInicio obrigatório.")
    @FutureOrPresent(message = "Não temos viagens ao passado.")   
    private LocalDate dataInicio;
    
    @NotBlank(message = "campo dataFim obrigatório.")
    @Future(message = "Deve ser no futuro.") //erro ao criar dataInicio maior que dataFim.
    private LocalDate dataFim;
}