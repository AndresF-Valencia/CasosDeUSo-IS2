package co.edu.uniquindio.CasosDeUso.controller;

import co.edu.uniquindio.CasosDeUso.model.Book;
import co.edu.uniquindio.CasosDeUso.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
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
}
