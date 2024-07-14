package com.aluracursos.challengeliteralura.repository;

import com.aluracursos.challengeliteralura.model.Book;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByLanguage(String language);

    Optional<Book> findByTitleIgnoreCase(String title);

    @Query("SELECT b FROM Book b ORDER BY b.downloadCount DESC")
    List<Book> findTop10Books(Pageable pageable);
}
