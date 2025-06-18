package br.com.victorcosta.libraryapi.modules.author.controllers;

import br.com.victorcosta.libraryapi.modules.author.useCases.ListAuthorsUseCase;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authors")
public class ListAuthorsController {
    private final ListAuthorsUseCase listAuthorsUseCase;

    public ListAuthorsController(ListAuthorsUseCase listAuthorsUseCase) {
        this.listAuthorsUseCase = listAuthorsUseCase;
    }

    @GetMapping("/list")
    public Object list(@RequestParam(required = false) String fullNAme, @PageableDefault(sort = "fullName", direction = Sort.Direction.ASC) Pageable pageable, HttpServletRequest request) {
        var userId = request.getAttribute("user_id");

        if(userId != null) {
            return this.listAuthorsUseCase.execute(fullNAme, pageable);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED);
    }
}
