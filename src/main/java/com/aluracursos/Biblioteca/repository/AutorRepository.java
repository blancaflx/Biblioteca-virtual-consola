package com.aluracursos.Biblioteca.repository;

import com.aluracursos.Biblioteca.model.Autor;
import com.aluracursos.Biblioteca.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<Autor, Long> {
}
