package co.edu.uniquindio.CasosDeUso.controller;

import co.edu.uniquindio.CasosDeUso.model.Book;
import co.edu.uniquindio.CasosDeUso.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/search")
    public ResponseEntity<List<Book>> search(@RequestParam("q") String term) {
        List<Book> results = bookService.searchBooks(term);
        return ResponseEntity.ok(results);
    }

    // HU2: búsqueda por cualquier combinación de título, autor e ISBN
    @GetMapping("/search/advanced")
    public ResponseEntity<?> searchAdvanced(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "author", required = false) String author,
            @RequestParam(value = "isbn", required = false) String isbn) {

        try {
            List<Book> results = bookService.searchBooksAdvanced(title, author, isbn);
            return ResponseEntity.ok(results);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}