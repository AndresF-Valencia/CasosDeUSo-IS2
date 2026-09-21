package co.edu.uniquindio.CasosDeUso.controller;

import co.edu.uniquindio.CasosDeUso.model.Review;
import co.edu.uniquindio.CasosDeUso.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // HU4: escribir una reseña de un libro, con vista previa antes de enviarla

    @PostMapping("/preview")
    public ResponseEntity<?> previewReview(@RequestBody ReviewRequest request) {
        try {
            Review preview = reviewService.previewReview(request.bookId(), request.username(), request.content());
            return ResponseEntity.ok(preview);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> createReview(@RequestBody ReviewRequest request) {
        try {
            Review review = reviewService.createReview(request.bookId(), request.username(), request.content());
            return ResponseEntity.status(HttpStatus.CREATED).body(review);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/book/{bookId}")
    public ResponseEntity<List<Review>> getReviewsForBook(@PathVariable Long bookId) {
        return ResponseEntity.ok(reviewService.getReviewsForBook(bookId));
    }

    // Cuerpo esperado en el POST: { "bookId": 1, "username": "sofi", "content": "Muy buen libro..." }
    public record ReviewRequest(Long bookId, String username, String content) {
    }
}