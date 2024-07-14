package com.aluracursos.challengeliteralura.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Optional;

@Entity
@Table(name = "books")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String title;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Author author;
    private String language;
    @Column(name = "downloadCount")
    private Integer downloadCount;

    public Book(BookInfo book) {
        this.title = book.title();
        Optional<AuthorInfo> author = book.authors().stream()
                .findFirst();
        if (author.isPresent()) {
            this.author = new Author(author.get());
        } else {
            System.out.println("Autor no encontrado");
        }
        this.language = book.language().get(0);
        this.downloadCount = book.downloadCount();
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

}
