package br.edu.utfpr.commerceapi.dto;
import java.time.LocalDate;
import jakarta.validation.constraints.FutureOrPresent;
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

public class ReservaDTO {
    @NotBlank(message = "campo dataReserva obrigatório.")
    @FutureOrPresent(message = "Deve ser no presente ou futuro.")
    private LocalDate dataReserva;
}