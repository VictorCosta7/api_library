package br.com.victorcosta.libraryapi.modules.user.controllers;

import br.com.victorcosta.libraryapi.modules.user.dto.UpdateUserDto;
import br.com.victorcosta.libraryapi.modules.user.useCases.UpdateUserUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UpdateUserController {
    private final UpdateUserUseCase updateUserUseCase;

    public UpdateUserController(UpdateUserUseCase updateUserUseCase) {
        this.updateUserUseCase = updateUserUseCase;
    }

    @PatchMapping()
    public ResponseEntity<Void> handler(
            @Valid @RequestBody UpdateUserDto updateUserDto, HttpServletRequest request) {
        Object userId = request.getAttribute("user_id");

        this.updateUserUseCase.execute(UUID.fromString(userId.toString()),updateUserDto);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
