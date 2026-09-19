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

    // HU2: búsqueda combinada por título, autor e ISBN (cualquier combinación, AND lógico)
    // Cada parámetro es opcional: si viene null, ese filtro no se aplica.
    @Query("SELECT b FROM Book b WHERE " +
            "(:title IS NULL OR LOWER(b.title) LIKE LOWER(CONCAT('%', :title, '%'))) AND " +
            "(:author IS NULL OR LOWER(b.author) LIKE LOWER(CONCAT('%', :author, '%'))) AND " +
            "(:isbn IS NULL OR LOWER(b.isbn) = LOWER(:isbn))")
    List<Book> searchByTitleAuthorIsbn(@Param("title") String title,
                                       @Param("author") String author,
                                       @Param("isbn") String isbn);
}