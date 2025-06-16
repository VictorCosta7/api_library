package br.com.victorcosta.libraryapi.modules.author.dto;

import java.time.LocalDate;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateAuthorDto {
    private String fullName;
    private String nationality;
    private LocalDate dateOfBirth;
    private UUID userId;
}
