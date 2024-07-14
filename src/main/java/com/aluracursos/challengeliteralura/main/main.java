package com.aluracursos.challengeliteralura.main;

import com.aluracursos.challengeliteralura.model.Author;
import com.aluracursos.challengeliteralura.model.result;
import com.aluracursos.challengeliteralura.model.BookInfo;
import com.aluracursos.challengeliteralura.model.Book;
import com.aluracursos.challengeliteralura.repository.AuthorRepository;
import com.aluracursos.challengeliteralura.repository.BookRepository;
import com.aluracursos.challengeliteralura.service.API;
import com.aluracursos.challengeliteralura.service.getData;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class main {
    private static final String APIURL = "https://gutendex.com/books/";
    private API api = new API();
    private getData getdata = new getData();
    private Scanner scanner = new Scanner(System.in);
    private BookRepository bookRepository;
    private AuthorRepository authorRepository;

    public main(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    private void readBook(Book book) {
        System.out.printf("\n\n" + """


            =======================
            |        LIBRO        |
            =======================
            Título : %s
            Autor  : %s
            Idioma : %s
            Descargas : %d
            =======================
            \n""",
                book.getTitle(),
                book.getAuthor().getName(),
                book.getLanguage(),
                book.getDownloadCount());
    }

    private void findBook() {
        System.out.println("Introduzca el nombre del libro a buscar:");
        scanner.nextLine();
        String bookName = scanner.nextLine();

        if (bookName.isEmpty()) {
            System.out.println("Debe ingresar un nombre de libro válido.");
            return;
        }

        Optional<Book> existingBook = bookRepository.findByTitleIgnoreCase(bookName);

        if (existingBook.isPresent()) {
            System.out.println("El libro ya existe en la base de datos.");
            readBook(existingBook.get());
        } else {
            String json = api.getBooks(APIURL + "?search=" + bookName.replace(" ", "+"));

            List<BookInfo> books = getdata.Data(json, result.class).result();

            Optional<BookInfo> bookOptional = books.stream()
                    .filter(b -> b.title().toLowerCase().contains(bookName.toLowerCase()))
                    .findFirst();

            if (bookOptional.isPresent()) {
                BookInfo bookInfo = bookOptional.get();
                var book = new Book(bookInfo);
                bookRepository.save(book);
                readBook(book);
            } else {
                System.out.println("Libro no encontrado");
            }
        }
    }

    private void listBooks() {
        List<Book> books = bookRepository.findAll();
        books.stream()
                .forEach(this::readBook);
    }

    private void readAuthor(Author author) {
        System.out.printf("\n\n" + """
            ===========================================
            |          INFORMACIÓN DEL AUTOR          |
            ===========================================
            Autor                : %s
            Fecha de Nacimiento   : %s
            Fecha de Fallecimiento: %s
            ------------------------------
            Libros del Autor:
            ------------------------------
            """,
                author.getName(),
                author.getBirthYear(),
                author.getDeathYear());

        var books = author.getBooks().stream()
                .map(a -> a.getTitle())
                .collect(Collectors.toList());
        System.out.println("Libros : " + books + "\n");
    }

    private void listarAutores() {
        List<Author> authors = authorRepository.findAll();
        authors.stream()
                .forEach(this::readAuthor);
    }

    private void listAuthorByYear() {
        System.out.println("Introduzca el año en que vivió el autor que desea buscar");
        try {
            Integer year = scanner.nextInt();
            List<Author> authors = authorRepository.findByDeathYearGreaterThan(year);
            authors.forEach(this::readAuthor);
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Por favor, introduzca un número entero válido.");
            scanner.next();
        }
    }

    private void listBooksByLanguage() {
        System.out.println("\n\n" + """
            =======================================
            |    BÚSQUEDA DE LIBROS POR IDIOMA    |
            =======================================
            Opciones disponibles:
            es -> Español
            en -> Inglés
            fr -> Francés
            pt -> Portugués
            -------------------------------
            Introduce el código del idioma:
            """);
        String language = scanner.next();
        List<Book> books = bookRepository.findByLanguage(language);
        books.stream()
                .forEach(this::readBook);
    }

    private void listTop10Books() {
        Pageable pageable = PageRequest.of(0, 10);
        List<Book> top10Books = bookRepository.findTop10Books(pageable);

        top10Books.forEach(this::readBook);
    }

    private void findAuthorByName() {
        System.out.println("Introduzca el nombre del autor a buscar");
        scanner.nextLine();
        var name = scanner.nextLine();
        authorRepository.findByName(name).stream()
                .forEach(this::readAuthor);
    }

    public void showMenu() {
        var option = -1;
        while (option != 8) {
            var menu = """
                      
            ---------------------------------
            |         MENÚ PRINCIPAL         |
            ---------------------------------
            
            Elija la opción a través de su número:
            1- Buscar libro por título
            2- Mostrar libros registrados
            3- Mostrar autores registrados
            4- Mostrar autores vivos en un determinado año
            5- Mostrar libros por idioma
            6- Mostrar el top 10 de libros más descargados
            7- Buscar autor por nombre
            8- Salir
            
            ---------------------------------
            Por favor, seleccione una opción: 
            """;
            System.out.println("\n\n" + menu);
            option = scanner.nextInt();
            switch (option) {
                case 1:
                    findBook();
                    break;
                case 2:
                    listBooks();
                    break;
                case 3:
                    listarAutores();
                    break;
                case 4:
                    listAuthorByYear();
                    break;
                case 5:
                    listBooksByLanguage();
                    break;
                case 6:
                    listTop10Books();
                    break;
                case 7:
                    findAuthorByName();
                    break;
                case 8:
                    System.out.println("Saliendo...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción no valida.");
            }
        }
    }
}
