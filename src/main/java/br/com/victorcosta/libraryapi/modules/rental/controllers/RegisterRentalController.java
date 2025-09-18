package br.com.victorcosta.libraryapi.modules.rental.controllers;

import br.com.victorcosta.libraryapi.modules.rental.dto.RegisterRentalDto;
import br.com.victorcosta.libraryapi.modules.rental.useCases.RegisterRentalUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
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
    public ResponseEntity<Object> handler(@PathVariable UUID bookId ,@RequestBody RegisterRentalDto registerRentalDto, HttpServletRequest request) {
        var userId = request.getAttribute("user_id");

        var rental = registerRentalUseCase.execute(UUID.fromString(userId.toString()), bookId, registerRentalDto.rentalDate());

        return ResponseEntity.ok().body(rental);
    }
}
