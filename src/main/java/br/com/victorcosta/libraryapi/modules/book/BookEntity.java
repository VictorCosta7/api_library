package br.com.victorcosta.libraryapi.modules.book;

import java.util.UUID;

import br.com.victorcosta.libraryapi.modules.author.AuthorEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "books")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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

    @ManyToOne
    @JoinColumn(name = "author_id", insertable = false, updatable = false)
    private AuthorEntity author;

    @Column(name = "author_id")
    private UUID authorId;
}
