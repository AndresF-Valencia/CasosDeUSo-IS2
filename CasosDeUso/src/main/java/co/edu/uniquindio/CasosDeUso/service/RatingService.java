package co.edu.uniquindio.CasosDeUso.service;

import co.edu.uniquindio.CasosDeUso.model.Book;
import co.edu.uniquindio.CasosDeUso.model.Rating;
import co.edu.uniquindio.CasosDeUso.repository.BookRepository;
import co.edu.uniquindio.CasosDeUso.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {

    private final RatingRepository ratingRepository;
    private final BookRepository bookRepository;

    @Autowired
    public RatingService(RatingRepository ratingRepository, BookRepository bookRepository) {
        this.ratingRepository = ratingRepository;
        this.bookRepository = bookRepository;
    }

    // HU3: calificar un libro de 1 (malo) a 5 (bueno). El libro no tiene que haber sido comprado por el usuario, así que no se valida ninguna compra.

    public Rating rateBook(Long bookId, String username, int score) {
        if (score < 1 || score > 5) {
            throw new IllegalArgumentException("La puntuación debe estar entre 1 y 5.");
        }
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Debe indicar el usuario que califica.");
        }

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("No existe un libro con id " + bookId));

        Rating rating = new Rating(null, book.getId(), username.trim(), score);
        return ratingRepository.save(rating);
    }

    // Lista todas las calificaciones de un libro

    public List<Rating> getRatingsForBook(Long bookId) {
        return ratingRepository.findByBookId(bookId);
    }

    // Promedio de calificaciones de un libro

    public double getAverageRating(Long bookId) {
        List<Rating> ratings = ratingRepository.findByBookId(bookId);
        if (ratings.isEmpty()) {
            return 0.0;
        }
        return ratings.stream()
                .mapToInt(Rating::getScore)
                .average()
                .orElse(0.0);
    }
}