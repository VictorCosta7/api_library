package br.com.victorcosta.libraryapi.modules.rental.useCases;

import br.com.victorcosta.libraryapi.exeptions.BookNotAvailableException;
import br.com.victorcosta.libraryapi.exeptions.BookNotFoundException;
import br.com.victorcosta.libraryapi.exeptions.UserNotFoundException;
import br.com.victorcosta.libraryapi.modules.book.domain.BookEntity;
import br.com.victorcosta.libraryapi.modules.book.domain.BookStatus;
import br.com.victorcosta.libraryapi.modules.book.repositories.BookRepository;
import br.com.victorcosta.libraryapi.modules.rental.RentalEntity;
import br.com.victorcosta.libraryapi.modules.rental.repository.RentalRepository;
import br.com.victorcosta.libraryapi.modules.user.UserEntity;
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
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        BookEntity book = bookRepository.findById(bookId).orElseThrow(BookNotFoundException::new);

        if (book.getQuantity() == 0) {
            throw new BookNotAvailableException();
        }

        book.setQuantity(book.getQuantity() - 1);

        if (book.getQuantity() == 0) {
            book.setStatus(BookStatus.RENTED);
        } else {
            book.setStatus(BookStatus.AVAILABLE);
        }

        LocalDateTime rentalDateNow = LocalDateTime.now();
        LocalDateTime dueDate = rentalDateNow.plusDays(30);

        RentalEntity rental = new RentalEntity(
                user.getId(), book.getId(), rentalDateNow, dueDate
        );

        bookRepository.save(book);

        return rentalRepository.save(rental);
    }
}
