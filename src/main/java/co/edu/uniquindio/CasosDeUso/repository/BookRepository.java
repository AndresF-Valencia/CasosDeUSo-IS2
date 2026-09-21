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
    // Nota: se castea cada parámetro a "string" porque PostgreSQL no puede inferir
    // el tipo de un parámetro que solo se compara con IS NULL (error: "could not
    // determine data type of parameter"). El CAST le da el tipo explícitamente.
    @Query("SELECT b FROM Book b WHERE " +
            "(CAST(:title AS string) IS NULL OR LOWER(b.title) LIKE LOWER(CONCAT('%', CAST(:title AS string), '%'))) AND " +
            "(CAST(:author AS string) IS NULL OR LOWER(b.author) LIKE LOWER(CONCAT('%', CAST(:author AS string), '%'))) AND " +
            "(CAST(:isbn AS string) IS NULL OR LOWER(b.isbn) = LOWER(CAST(:isbn AS string)))")
    List<Book> searchByTitleAuthorIsbn(@Param("title") String title,
                                       @Param("author") String author,
                                       @Param("isbn") String isbn);
}