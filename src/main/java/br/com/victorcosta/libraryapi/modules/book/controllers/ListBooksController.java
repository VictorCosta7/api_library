package br.com.victorcosta.libraryapi.modules.book.controllers;

import br.com.victorcosta.libraryapi.modules.book.useCases.ListBooksUseCase;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class ListBooksController {
    private final ListBooksUseCase listBooksUseCase;

    public ListBooksController(ListBooksUseCase listBooksUseCase) {
        this.listBooksUseCase = listBooksUseCase;
    }

    @GetMapping("/list")
    public Object list(@RequestParam(required = false) String filter,
                       Pageable pageable, HttpServletRequest request) {
        var userId = request.getAttribute("user_id");

        if(userId != null) {
            return this.listBooksUseCase.execute(filter, pageable);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED);
    }
}
