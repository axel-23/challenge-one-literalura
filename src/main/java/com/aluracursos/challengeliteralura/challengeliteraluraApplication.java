package com.aluracursos.challengeliteralura;

import com.aluracursos.challengeliteralura.main.main;
import com.aluracursos.challengeliteralura.repository.AuthorRepository;
import com.aluracursos.challengeliteralura.repository.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class challengeliteraluraApplication implements CommandLineRunner {
	@Autowired
	BookRepository bookRepository;
	@Autowired
	AuthorRepository authorRepository;

	public static void main(String[] args) {
		SpringApplication.run(challengeliteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		main main = new main(bookRepository, authorRepository);
		main.showMenu();
	}
}