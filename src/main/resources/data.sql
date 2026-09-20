INSERT INTO books (id, title, author, isbn) VALUES
(1, 'El senor de los anillos', 'J.R.R. Tolkien', '9780618640157'),
(2, 'El hobbit', 'J.R.R. Tolkien', '9780547928227'),
(3, 'Cien anos de soledad', 'Gabriel Garcia Marquez', '9780307474728'),
(4, '1984', 'George Orwell', '9780451524935'),
(5, 'Rebelion en la granja', 'George Orwell', '9780451526342'),
(6, 'Rayuela', 'Julio Cortazar', '9788437604572'),
(7, 'La ciudad y los perros', 'Mario Vargas Llosa', '9788466333610'),
(8, 'Ficciones', 'Jorge Luis Borges', '9780802130303'),
(9, 'Don Quijote de la Mancha', 'Miguel de Cervantes', '9788420412146'),
(10, 'Cronica de una muerte anunciada', 'Gabriel Garcia Marquez', '9780307387738');

INSERT INTO ratings (id, book_id, username, score) VALUES
(1, 1, 'sofi', 5),
(2, 1, 'mateo', 4),
(3, 1, 'valentina', 5),
(4, 2, 'andres', 4),
(5, 2, 'camila', 5),
(6, 3, 'laura', 5),
(7, 3, 'diego', 4),
(8, 3, 'natalia', 5),
(9, 4, 'santiago', 5),
(10, 4, 'isabella', 4),
(11, 5, 'juan', 4),
(12, 6, 'mariana', 3),
(13, 7, 'sebastian', 4),
(14, 8, 'daniela', 5),
(15, 10, 'lucas', 4);

INSERT INTO reviews (id, book_id, username, content) VALUES
(1, 1, 'sofi', 'Excelente fantasia epica, ideal para probar busquedas por Tolkien.'),
(2, 1, 'mateo', 'Tiene un ritmo lento al inicio, pero el mundo esta muy bien construido.'),
(3, 2, 'andres', 'Aventura corta y facil de recomendar.'),
(4, 3, 'laura', 'Una novela clave para probar autores latinoamericanos.'),
(5, 3, 'natalia', 'La historia familiar permite hacer buenas pruebas de contenido.'),
(6, 4, 'santiago', 'Distopia directa y muy util para pruebas de busqueda por titulo exacto.'),
(7, 5, 'juan', 'Lectura breve con critica politica clara.'),
(8, 6, 'mariana', 'Experimental y exigente, buena para tener variedad de opiniones.'),
(9, 8, 'daniela', 'Cuentos precisos, sirve para probar otro autor argentino.'),
(10, 10, 'lucas', 'Corta, intensa y facil de ubicar por ISBN.');

ALTER SEQUENCE books_id_seq RESTART WITH 11;
ALTER SEQUENCE ratings_id_seq RESTART WITH 16;
ALTER SEQUENCE reviews_id_seq RESTART WITH 11;
