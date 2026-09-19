package co.edu.uniquindio.CasosDeUso.service;

import co.edu.uniquindio.CasosDeUso.model.Book;
import co.edu.uniquindio.CasosDeUso.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // HU1: búsqueda básica por título o autor
    public List<Book> searchBooks(String term) {
        if (term == null || term.isBlank()) {
            return List.of();
        }
        return bookRepository.searchByTitleOrAuthor(term.trim());
    }

    // HU2: búsqueda combinada por título, autor e ISBN
    public List<Book> searchBooksAdvanced(String title, String author, String isbn) {
        String normalizedTitle = normalize(title);
        String normalizedAuthor = normalize(author);
        String normalizedIsbn = normalize(isbn);

        if (normalizedTitle == null && normalizedAuthor == null && normalizedIsbn == null) {
            throw new IllegalArgumentException(
                    "Debe ingresar al menos un criterio de búsqueda: título, autor o ISBN.");
        }

        return bookRepository.searchByTitleAuthorIsbn(normalizedTitle, normalizedAuthor, normalizedIsbn);
    }

    // Convierte cadenas vacías o en blanco a null para que el filtro no se aplique
    private String normalize(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        return value.trim();
    }
}