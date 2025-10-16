package br.com.victorcosta.libraryapi.modules.user.controllers;

import br.com.victorcosta.libraryapi.modules.user.domain.UserEntity;
import br.com.victorcosta.libraryapi.modules.user.useCases.ProfileUserUseCase;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class ProfileUserController {
    private final ProfileUserUseCase profileUserUseCase;

    public ProfileUserController(ProfileUserUseCase profileUserUseCase) {
        this.profileUserUseCase = profileUserUseCase;
    }

    @GetMapping("/me")
    @Operation(
            summary = "Retrieve the profile of the authenticated user",
            description = "Retrieves the complete profile data for the currently logged-in user. The User ID is automatically extracted from the JWT/Authentication token in the request header."
    )
    public ResponseEntity<Object> handler(HttpServletRequest request) {
        Object userId = request.getAttribute("user_id");

       UserEntity user = this.profileUserUseCase.execute(UUID.fromString(userId.toString()));

       return ResponseEntity.ok().body(user);
    }
}
