package br.com.victorcosta.libraryapi.modules.book.domain;

import br.com.victorcosta.libraryapi.modules.user.domain.UserEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity(name = "books")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String isbn;

    private String title;

    private List<String> authors;

    private String publisher;

    @Column(columnDefinition = "TEXT", length = 2000)
    private String synopsis;

    private Integer year;

    @Column(name = "page_count")
    private Integer pageCount;

    private List<String> subjects;

    @Column(name = "cover_url")
    private String coverUrl;

    @Column(name = "user_id")
    private UUID userId;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private UserEntity user;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private BookStatus status = BookStatus.AVAILABLE;

    private Integer quantity;

    public UUID getId() {
        return id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public List<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public BookStatus getStatus() { return status; }

    public void setStatus(BookStatus status) { this.status = status; }

    public Integer getQuantity() { return quantity; }

    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public BookEntity(
            String isbn,
            String title,
            List<String> authors,
            String publisher,
            String synopsis,
            Integer year,
            Integer pageCount,
            List<String> subjects,
            String coverUrl,
            UUID userId,
            Integer quantity
                ) {
       this.isbn = isbn;
       this.title = title;
       this.authors = authors;
       this.publisher = publisher;
       this.synopsis = synopsis;
       this.year = year;
       this.pageCount = pageCount;
       this.subjects = subjects;
       this.coverUrl = coverUrl;
       this.userId = userId;
       this.quantity = quantity;
    }

    public BookEntity(
            UUID id,
            String isbn,
            String title,
            List<String> authors,
            String publisher,
            String synopsis,
            Integer year,
            Integer pageCount,
            List<String> subjects,
            String coverUrl,
            UserEntity user,
            LocalDateTime createdAt,
            Integer quantity
    ) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.authors = authors;
        this.publisher = publisher;
        this.synopsis = synopsis;
        this.year = year;
        this.pageCount = pageCount;
        this.subjects = subjects;
        this.coverUrl = coverUrl;
        this.user = user;
        this.createdAt = createdAt;
        this.quantity = quantity;
    }

    public BookEntity() {
    }

    @Override
    public String toString() {
        return "BookEntity{" +
                "id=" + id +
                ", isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", authors=" + authors +
                ", publisher='" + publisher + '\'' +
                ", synopsis='" + synopsis + '\'' +
                ", year=" + year +
                ", pageCount=" + pageCount +
                ", subjects=" + subjects +
                ", coverUrl='" + coverUrl + '\'' +
                ", userId=" + userId +
                ", user=" + user +
                ", createdAt=" + createdAt +
                ", status=" + status +
                ", quantity=" + quantity +
                '}';
    }
}