package br.edu.utfpr.commerceapi.dto;

import br.edu.utfpr.commerceapi.models.Pacote;
import br.edu.utfpr.commerceapi.models.Person;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
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
public class AvaliacaoDTO {

    @Size(min=3, max=100, message = "O título é obrigatório.")
    private String titulo;

    @NotBlank(message = "A nota é obrigatória.")
    @Min(value = 1, message = "Valor mínimo 1.")
    @Max(value = 5, message = "Valor máximo 5.")
    private int nota;

    @NotBlank(message = "Data da avaliação é obrigatória.")
    @PastOrPresent(message = "Não cadastramos futuristas.")
    private LocalDate dataAvaliacao;
    
    @NotBlank(message = "Comentário obrigatório.")
    @Size(min=10, max=500, message = "Deve conter entre 20 e 500 caracteres.")
    private String comentario;

    @NotBlank(message = "ID do passeio obrigatório.")
    private Pacote pacote;

    @NotBlank(message = "ID do cliente obrigatório.")
    private Person cliente;
}