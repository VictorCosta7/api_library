package br.com.victorcosta.libraryapi.modules.user.dto;

import java.util.List;

public record AuthUserResponseDTO(
         String access_token,
         Long expires_in,
         List<String> roles
) {
}
