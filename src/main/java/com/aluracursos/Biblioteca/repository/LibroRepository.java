package com.aluracursos.Biblioteca.repository;


import com.aluracursos.Biblioteca.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    @Query("SELECT COUNT(l) FROM Libro l WHERE :idioma MEMBER OF l.idiomas")
    Long contarLibrosPorIdioma(String idioma);
}
