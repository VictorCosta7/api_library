package br.com.victorcosta.libraryapi.modules.book.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import br.com.victorcosta.libraryapi.modules.author.AuthorEntity;
import br.com.victorcosta.libraryapi.modules.user.UserEntity;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity(name = "books")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Column
    private String isbn;

    @Column(name = "publication_year")
    private String publicationYear;

    @Column
    @Nullable
    private String edition;

    @Column
    @Enumerated(EnumType.STRING)
    private BookCategory bookCategory;

    private Integer numberOfPages;

    @Column(name = "author_id")
    private UUID authorId;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "author_id", insertable = false, updatable = false)
    private AuthorEntity author;

    @Column(name = "user_id")
    private UUID userId;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private UserEntity user;

    @Nullable
    public String getEdition() {
        return edition;
    }

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public void setEdition(@Nullable String edition) {
        this.edition = edition;
    }

    public BookCategory getBookCategory() {
        return bookCategory;
    }

    public void setBookCategory(BookCategory bookCategory) {
        this.bookCategory = bookCategory;
    }

    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public UUID getAuthorId() {
        return authorId;
    }

    public void setAuthorId(UUID authorId) {
        this.authorId = authorId;
    }

    public AuthorEntity getAuthor() {
        return author;
    }

    public void setAuthor(AuthorEntity author) {
        this.author = author;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(String publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookEntity that = (BookEntity) o;
        return java.util.Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(id);
    }

    @Override
    public String toString() {
        return "BookEntity{" + "id=" + id + ", title='" + title + '\'' + ", isbn='" + isbn + '\'' + ", publicationYear='" + publicationYear + '\'' + ", createdAt=" + createdAt + '}';
    }
}
