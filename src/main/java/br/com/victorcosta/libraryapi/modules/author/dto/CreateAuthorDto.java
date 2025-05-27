package br.com.victorcosta.libraryapi.modules.author.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateAuthorDto {
    private String fullNAme;
    private String email;
    private String password;
    private String nationality;
    private LocalDate dateOfBirth;
}
