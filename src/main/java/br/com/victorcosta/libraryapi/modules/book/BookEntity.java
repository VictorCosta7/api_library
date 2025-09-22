package br.com.victorcosta.libraryapi.modules.book;

import br.com.victorcosta.libraryapi.modules.user.UserEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Builder;
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

    private String subtitle;

    private List<String> authors;

    private String publisher;

    @Lob
    private String synopsis;

    private Integer year;

    private String format;

    @Column(name = "page_count")
    private Integer pageCount;

    private List<String> subjects;

    private String location;

    @Column(name = "retail_price")
    private Double retailPrice;

    @Column(name = "cover_url")
    private String coverUrl;

    private String provider;

    @Column(name = "user_id")
    private UUID userId;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private UserEntity user;

    @CreationTimestamp
    private LocalDateTime createdAt;

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

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
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

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(Double retailPrice) {
        this.retailPrice = retailPrice;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
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

    public BookEntity(
            String isbn,
            String title,
            String subtitle,
            List<String> authors,
            String publisher,
            String synopsis,
            Integer year,
            String format,
            Integer pageCount,
            List<String> subjects,
            String location,
            Double retailPrice,
            String coverUrl,
            String provider,
            UserEntity user
                ) {
       this.isbn = isbn;
       this.title = title;
       this.subtitle = subtitle;
       this.authors = authors;
       this.publisher = publisher;
       this.synopsis = synopsis;
       this.year = year;
       this.format = format;
       this.pageCount = pageCount;
       this.subjects = subjects;
       this.location = location;
       this.retailPrice = retailPrice;
       this.coverUrl = coverUrl;
       this.provider = provider;
       this.user = user;
    }

    public BookEntity(
            UUID id,
            String isbn,
            String title,
            String subtitle,
            List<String> authors,
            String publisher,
            String synopsis,
            Integer year,
            String format,
            Integer pageCount,
            List<String> subjects,
            String location,
            Double retailPrice,
            String coverUrl,
            String provider,
            UserEntity user,
            LocalDateTime createdAt
    ) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.subtitle = subtitle;
        this.authors = authors;
        this.publisher = publisher;
        this.synopsis = synopsis;
        this.year = year;
        this.format = format;
        this.pageCount = pageCount;
        this.subjects = subjects;
        this.location = location;
        this.retailPrice = retailPrice;
        this.coverUrl = coverUrl;
        this.provider = provider;
        this.user = user;
        this.createdAt = createdAt;
    }

    public BookEntity() {}

    @Override
    public String toString() {
        return "BookEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", isbn='" + isbn + '\'' +
                ", year='" + year + '\'' +
                ", publisher='" + publisher + '\'' +
                ", subjects=" + subjects +
                ", pageCount=" + pageCount +
                ", authors=" + authors +
                ", publisher='" + publisher + '\'' +
                ", synopsis='" + (synopsis != null && synopsis.length() > 50 ? synopsis.substring(0, 50) + "..." : synopsis) + '\'' + // Sinopse truncada
                ", format='" + format + '\'' +
                ", subjects=" + subjects +
                ", location='" + location + '\'' +
                ", retailPrice=" + retailPrice +
                ", coverUrl='" + coverUrl + '\'' +
                ", provider='" + provider + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}