package br.com.victorcosta.libraryapi.modules.user.dto;

import lombok.Data;

@Data
public class CreateUserDto {
    private String fullName;
    private String username;
    private String password;
    private String email;
}
