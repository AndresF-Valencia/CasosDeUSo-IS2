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
}