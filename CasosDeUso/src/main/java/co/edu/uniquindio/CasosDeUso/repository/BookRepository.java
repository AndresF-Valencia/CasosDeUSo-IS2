package co.edu.uniquindio.CasosDeUso.repository;

import co.edu.uniquindio.CasosDeUso.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    // Búsqueda genérica (título, autor o ISBN) - usada como base, puede servir para HU2
    @Query("SELECT b FROM Book b WHERE " +
            "LOWER(b.title) LIKE LOWER(CONCAT('%', :term, '%')) OR " +
            "LOWER(b.author) LIKE LOWER(CONCAT('%', :term, '%')) OR " +
            "LOWER(b.isbn) LIKE LOWER(CONCAT('%', :term, '%'))")
    List<Book> search(@Param("term") String term);

    // Búsqueda básica para HU1: solo título o autor
    @Query("SELECT b FROM Book b WHERE " +
            "LOWER(b.title) LIKE LOWER(CONCAT('%', :term, '%')) OR " +
            "LOWER(b.author) LIKE LOWER(CONCAT('%', :term, '%'))")
    List<Book> searchByTitleOrAuthor(@Param("term") String term);
}
