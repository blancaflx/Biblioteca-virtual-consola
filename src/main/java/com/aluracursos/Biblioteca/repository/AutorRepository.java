package com.aluracursos.Biblioteca.repository;

import com.aluracursos.Biblioteca.model.Autor;
import com.aluracursos.Biblioteca.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    @Query("SELECT a FROM Autor a WHERE a.añoNacimiento <= :anio AND (a.añoMuerte IS NULL OR a.añoMuerte >= :anio)")
    List<Autor> buscarAutoresVivosPorAnio(@Param("anio") int anio);
}
