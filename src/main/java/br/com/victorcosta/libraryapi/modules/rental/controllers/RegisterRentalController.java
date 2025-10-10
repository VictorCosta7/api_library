package br.com.victorcosta.libraryapi.modules.rental.controllers;

import br.com.victorcosta.libraryapi.modules.rental.domain.RentalEntity;
import br.com.victorcosta.libraryapi.modules.rental.useCases.RegisterRentalUseCase;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/rental")
public class RegisterRentalController {
    private final RegisterRentalUseCase registerRentalUseCase;

    public RegisterRentalController(RegisterRentalUseCase registerRentalUseCase) {
        this.registerRentalUseCase = registerRentalUseCase;
    }

    @PostMapping("/{bookId}")
    public ResponseEntity<Object> handler(@PathVariable UUID bookId , HttpServletRequest request) {
        Object userId = request.getAttribute("user_id");

        RentalEntity rental = registerRentalUseCase.execute(UUID.fromString(userId.toString()), bookId);

        return ResponseEntity.ok().body(rental);
    }
}
