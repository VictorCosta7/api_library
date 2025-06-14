package br.com.victorcosta.libraryapi.modules.user.dto;

import lombok.Data;

@Data
public class AuthUserDto {
    private String password;
    private String email;
}
