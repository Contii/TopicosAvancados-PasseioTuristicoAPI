package br.edu.utfpr.commerceapi.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

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


public class ReservaDTO {
    @NotNull(message = "campo dataReserva obrigatório.")
    @FutureOrPresent(message = "Deve ser no presente ou futuro.")
    private LocalDate dataReserva;
}