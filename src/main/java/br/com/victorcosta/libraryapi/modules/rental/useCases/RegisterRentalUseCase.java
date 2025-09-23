package br.com.victorcosta.libraryapi.modules.rental.useCases;

import br.com.victorcosta.libraryapi.exeptions.BookNotFoundException;
import br.com.victorcosta.libraryapi.exeptions.UserNotFoundException;
import br.com.victorcosta.libraryapi.modules.book.domain.BookStatus;
import br.com.victorcosta.libraryapi.modules.book.repositories.BookRepository;
import br.com.victorcosta.libraryapi.modules.rental.RentalEntity;
import br.com.victorcosta.libraryapi.modules.rental.repository.RentalRepository;
import br.com.victorcosta.libraryapi.modules.user.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class RegisterRentalUseCase {

    private final RentalRepository rentalRepository;

    private final UserRepository userRepository;

    private final BookRepository bookRepository;

    public RegisterRentalUseCase(RentalRepository rentalRepository, UserRepository userRepository, BookRepository bookRepository) {
        this.rentalRepository = rentalRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    public RentalEntity execute(UUID userId, UUID bookId) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> {
                    throw new UserNotFoundException();
                });

        var book = bookRepository.findById(bookId).orElseThrow(()-> {
            throw new BookNotFoundException();
        });

        var rentalDateNow = LocalDateTime.now();

        var dueDate = rentalDateNow.plusDays(30);

        var rental = new RentalEntity(
                user.getId(), book.getId(), rentalDateNow, dueDate
        );

        book.setStatus(BookStatus.RENTED);

        bookRepository.save(book);

        return rentalRepository.save(rental);
    }
}
