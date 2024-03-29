package br.edu.utfpr.commerceapi.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
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

public class PersonDTO {
    
    @NotBlank(message = "Nome obrigatório.")
    @Size(min=3, max=100, message = "Deve conter entre 3 e 100 caracteres.")
    private String name;

    @NotBlank(message = "Email obrigatório.")
    @Email(message = "E-mail obrigatório.")
    private String email;

    @NotBlank(message = "Senha obrigatório.")
    @Size(min=8, max=50, message = "Deve conter entre 8 e 50 caracteres.")
    private String password;

    private String phone;

    @PastOrPresent(message = "Não cadastramos futuristas.")
    private LocalDate birth;

    private String profile;

}