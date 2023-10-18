package br.edu.utfpr.commerceapi.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotBlank(message = "campo nome obrigatório.")
    @Size(min=3, max=100, message = "Deve conter entre 3 e 100 caracteres.")
    private String nome;

    @NotBlank(message = "campo descricao obrigatório.")
    @Size(min=20, max=500, message = "Deve conter entre 20 e 500 caracteres.")
    private String descricao;

    @NotNull(message = "campo valor obrigatório.")
    @Min(value = 50, message = "Valor mínimo 50.")
    @Max(value = 10000, message = "Valor máximo 10000.")
    private float valor;

    @NotNull(message = "campo duracaoEmDias obrigatório.")
    @Min(value = 1, message = "Duração em dias mínima 1.")
    @Max(value = 7, message = "Duração em dias máxima 7.")
    private int duracaoEmDias;

    @NotNull(message = "campo dataInicio obrigatório.")
    @FutureOrPresent(message = "Não temos viagens ao passado.")   
    private LocalDate dataInicio;
    
    @NotNull(message = "campo dataFim obrigatório.")
    @Future(message = "Deve ser no futuro.")
    private LocalDate dataFim;

    @NotNull(message = "campo vagasDisponiveis obrigatório.")
    @Min(value = 1, message = "Mínimo 1.")
    @Max(value = 7, message = "Máximo 5.")
    private int vagasDisponiveis;
}