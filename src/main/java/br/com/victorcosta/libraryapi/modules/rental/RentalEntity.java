package br.com.victorcosta.libraryapi.modules.rental;

import br.com.victorcosta.libraryapi.modules.book.BookEntity;
import br.com.victorcosta.libraryapi.modules.user.UserEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "rentals")
public class RentalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "user_id")
    private UUID userId;

    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private UserEntity user;

    @Column(name = "book_id")
    private UUID bookId;

    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "book_id", insertable = false, updatable = false)
    private BookEntity book;

    @Column(name = "rental_date")
    private LocalDateTime rentalDate;

    @Column(name = "due_date")
    private LocalDateTime dueDate;

    @Column
    private String status;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public UUID getBookId() {
        return bookId;
    }

    public void setBookId(UUID bookId) {
        this.bookId = bookId;
    }

    public BookEntity getBook() {
        return book;
    }

    public void setBook(BookEntity book) {
        this.book = book;
    }

    public LocalDateTime getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(LocalDateTime rentalDate) {
        this.rentalDate = rentalDate;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public RentalEntity(UUID userId, UUID bookId, LocalDateTime rentalDate, LocalDateTime dueDate, String status) {
        this.userId = userId;
        this.bookId = bookId;
        this.rentalDate = rentalDate;
        this.dueDate = dueDate;
        this.status = status;
    }

    @Override
    public String toString() {
        return "BookEntity{" +
                "id=" + id +
                ", userId=" + user.getId() +
                ", bookId=" + book.getId() +
                ", rentalDate=" + rentalDate +
                ", dueDate=" + dueDate +
                ", status=" + status  + '}';
    }
}
