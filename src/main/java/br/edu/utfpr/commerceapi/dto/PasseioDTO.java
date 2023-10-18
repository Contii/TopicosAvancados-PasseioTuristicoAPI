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
    @NotBlank(message = "campo nome obrigatório.")
    @Size(min=3, max=100, message = "Deve conter entre 3 e 100 caracteres.")
    private String nome;

    @NotBlank(message = "campo descricao obrigatório.")
    @Size(min=20, max=500, message = "Deve conter entre 20 e 500 caracteres.")
    private String descricao;

    @NotNull(message = "campo valor obrigatório.")
    @Min(value = 50, message = "Valor mínimo 50.")
    @Max(value = 5000, message = "Valor máximo 5000.")
    private float valor;

    @NotNull(message = "campo duracaoEmHoras obrigatório.")
    @Min(value = 1, message = "duração mínima 1 hora.")
    @Max(value = 72, message = "duraçao máxima 72 horas.")
    private int duracaoEmHoras;

    @NotNull(message = "campo vagasDisponiveis obrigatório.")
    @Min(value = 1, message = "Minimo de vagas 1.")
    @Max(value = 5, message = "Máximo de vagas 5.")
    private int vagasDisponiveis;

    @NotNull(message = "campo classificacao obrigatório.")
    @Min(value = 1, message = "Idade minima 1.")
    @Max(value = 23, message = "Idade máxima 23.")
    private int classificacao;
}