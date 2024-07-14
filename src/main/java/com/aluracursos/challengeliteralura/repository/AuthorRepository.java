package com.aluracursos.challengeliteralura.repository;

import com.aluracursos.challengeliteralura.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    List<Author> findByDeathYearGreaterThan(Integer deathYear);

    

    List<Author> findByName(String name);
}