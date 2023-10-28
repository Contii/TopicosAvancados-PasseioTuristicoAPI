package br.edu.utfpr.commerceapi.dto;

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


public class PasseioDTO {
    @NotBlank(message = "Nome obrigatório.")
    @Size(min=3, max=100, message = "Deve conter entre 3 e 100 caracteres.")
    private String name;

    @NotBlank(message = "Descricao obrigatória.")
    @Size(min=20, max=500, message = "Deve conter entre 20 e 500 caracteres.")
    private String description;

    @NotNull(message = "Preço obrigatório.")
    @Min(value = 50, message = "Preço mínimo 50.")
    @Max(value = 5000, message = "Preço máximo 5000.")
    private float price;

    @NotNull(message = "Duração em horas obrigatória.")
    @Min(value = 1, message = "duração mínima 1 hora.")
    @Max(value = 72, message = "duraçao máxima 72 horas.")
    private int durationInHours;

    @NotNull(message = "Vagas obrigatórias.")
    @Min(value = 1, message = "Minimo de vagas 1.")
    @Max(value = 5, message = "Máximo de vagas 5.")
    private int availableVacancies;

    @NotNull(message = "Classificação obrigatória.")
    @Min(value = 1, message = "Idade minima 1.")
    @Max(value = 23, message = "Idade máxima 23.")
    private int classification;

}