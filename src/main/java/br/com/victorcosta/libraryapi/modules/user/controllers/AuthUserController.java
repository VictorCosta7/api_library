package br.com.victorcosta.libraryapi.modules.user.controllers;

import br.com.victorcosta.libraryapi.modules.user.dto.AuthUserDto;
import br.com.victorcosta.libraryapi.modules.user.dto.AuthUserResponseDTO;
import br.com.victorcosta.libraryapi.modules.user.useCases.AuthUserUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;

@RestController()
@RequestMapping("/users")
public class AuthUserController {

    private final AuthUserUseCase authUserUseCase;

    public AuthUserController(AuthUserUseCase authUserUseCase) {
        this.authUserUseCase = authUserUseCase;
    }

    @PostMapping("/auth")
    @Tag(name = "Users", description = "Operations related to user authentication and management.")
    @Operation(
            summary = "User Login",
            description = "Authenticates a user using their email and password, returning an access token upon success."
    )
    public ResponseEntity<Object> handler(@Valid @RequestBody AuthUserDto authUserDto) throws AuthenticationException {
        AuthUserResponseDTO result = this.authUserUseCase.execute(authUserDto);

       return  ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
