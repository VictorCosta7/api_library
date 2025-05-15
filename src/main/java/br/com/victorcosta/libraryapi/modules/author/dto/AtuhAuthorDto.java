package br.com.victorcosta.libraryapi.modules.author.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AtuhAuthorDto {
    private String email;
    private String password;
}
