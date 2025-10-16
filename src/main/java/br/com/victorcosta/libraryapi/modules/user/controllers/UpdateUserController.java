package br.com.victorcosta.libraryapi.modules.user.controllers;

import br.com.victorcosta.libraryapi.modules.user.dto.UpdateUserDto;
import br.com.victorcosta.libraryapi.modules.user.useCases.UpdateUserUseCase;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UpdateUserController {
    private final UpdateUserUseCase updateUserUseCase;

    public UpdateUserController(UpdateUserUseCase updateUserUseCase) {
        this.updateUserUseCase = updateUserUseCase;
    }

    @PatchMapping("/me")
    @Operation(
            summary = "Partially update the authenticated user",
            description = "Updates one or more fields for the authenticated user. The user ID is obtained from the authentication token (JWT). **All fields in the request body are optional.**"
    )
    public ResponseEntity<Void> handler(
            @RequestBody UpdateUserDto updateUserDto, HttpServletRequest request) {
        Object userId = request.getAttribute("user_id");

        this.updateUserUseCase.execute(UUID.fromString(userId.toString()),updateUserDto);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
