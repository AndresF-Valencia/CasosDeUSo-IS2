package co.edu.uniquindio.CasosDeUso.repository;

import co.edu.uniquindio.CasosDeUso.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    // Obtener todas las reseñas pertenecientes a un libro específico
    List<Review> findByBookId(Long bookId);
}
