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

    @NotBlank(message = "Nome obrigatório.")
    @Size(min=3, max=100, message = "Deve conter entre 3 e 100 caracteres.")
    private String name;

    @NotBlank(message = "Descrição obrigatória.")
    @Size(min=20, max=500, message = "Deve conter entre 20 e 500 caracteres.")
    private String description;

    @NotNull(message = "Preço obrigatório.")
    @Min(value = 50, message = "Valor mínimo 50.")
    @Max(value = 10000, message = "Valor máximo 10000.")
    private float price;

    @NotNull(message = "Duração obrigatória.")
    @Min(value = 1, message = "Duração em dias mínima 1.")
    @Max(value = 7, message = "Duração em dias máxima 7.")
    private int durationInDays;

    @NotNull(message = "Data de início obrigatória.")
    @FutureOrPresent(message = "Não temos viagens ao passado.")   
    private LocalDate beginDate;
    
    @NotNull(message = "Data final obrigatória.")
    @Future(message = "Deve ser no futuro.")
    private LocalDate endDate;

    @NotNull(message = "Vagas obrigatórias.")
    @Min(value = 1, message = "Mínimo 1.")
    @Max(value = 7, message = "Máximo 5.")
    private int availableVacancies;
    
}