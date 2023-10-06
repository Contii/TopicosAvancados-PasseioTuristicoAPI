package br.edu.utfpr.commerceapi.dto;
import java.time.LocalDate;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
public class PacoteDTO {
    private String nome;
    private String descricao;
    private float valor;
    private int duracaoEmDias;  
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private int vagasDisponiveis;
}