package co.edu.uniquindio.CasosDeUso.controller;

import co.edu.uniquindio.CasosDeUso.model.Rating;
import co.edu.uniquindio.CasosDeUso.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {

    private final RatingService ratingService;

    @Autowired
    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    // HU3: calificar un libro de 1 a 5

    @PostMapping
    public ResponseEntity<?> rateBook(@RequestBody RatingRequest request) {
        try {
            Rating rating = ratingService.rateBook(request.bookId(), request.username(), request.score());
            return ResponseEntity.status(HttpStatus.CREATED).body(rating);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/book/{bookId}")
    public ResponseEntity<List<Rating>> getRatingsForBook(@PathVariable Long bookId) {
        return ResponseEntity.ok(ratingService.getRatingsForBook(bookId));
    }

    @GetMapping("/book/{bookId}/average")
    public ResponseEntity<Double> getAverageRating(@PathVariable Long bookId) {
        return ResponseEntity.ok(ratingService.getAverageRating(bookId));
    }

    // Cuerpo esperado en el POST: { "bookId": 1, "username": "sofi", "score": 5 }
    public record RatingRequest(Long bookId, String username, int score) {
    }
}