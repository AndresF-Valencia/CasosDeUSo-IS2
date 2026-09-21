package co.edu.uniquindio.CasosDeUso.service;

import co.edu.uniquindio.CasosDeUso.model.Review;
import co.edu.uniquindio.CasosDeUso.repository.BookRepository;
import co.edu.uniquindio.CasosDeUso.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final BookRepository bookRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository, BookRepository bookRepository) {
        this.reviewRepository = reviewRepository;
        this.bookRepository = bookRepository;
    }

    // Genera la reseña para que el usuario la vea antes de confirmar, sin guardarla en la BD
    public Review previewReview(Long bookId, String username, String content) {
        validateReview(bookId, username, content);
        Review preview = new Review();
        preview.setBookId(bookId);
        preview.setUsername(username);
        preview.setContent(content);
        return preview;
    }

    // HU4: escribir una reseña de un libro (no tiene que haber sido comprado por el usuario)
    public Review createReview(Long bookId, String username, String content) {
        validateReview(bookId, username, content);
        Review review = new Review();
        review.setBookId(bookId);
        review.setUsername(username);
        review.setContent(content);
        return reviewRepository.save(review);
    }

    public List<Review> getReviewsForBook(Long bookId) {
        return reviewRepository.findByBookId(bookId);
    }

    private void validateReview(Long bookId, String username, String content) {
        if (!bookRepository.existsById(bookId)) {
            throw new IllegalArgumentException("El libro con id " + bookId + " no existe");
        }
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("El nombre de usuario no puede estar vacío");
        }
        if (content == null || content.isBlank()) {
            throw new IllegalArgumentException("La reseña no puede estar vacía");
        }
    }
}