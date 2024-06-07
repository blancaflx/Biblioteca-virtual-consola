package com.aluracursos.Biblioteca.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;
@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;

    @OneToMany(mappedBy = "libro", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Autor> autores;

    @ElementCollection
    private List<String> idiomas;

    private Integer numeroDeDescargas;

    public Libro() {}

    public Libro(Datos datos) {
        if (datos != null && datos.resultados() != null && !datos.resultados().isEmpty()) {
            // Supongamos que solo queremos utilizar el primer resultado de la lista
            DatosLibro primerResultado = datos.resultados().get(0);

            // Asignar los valores del primer resultado a los atributos del libro
            this.numeroDeDescargas = primerResultado.numeroDescargas();
            this.idiomas = primerResultado.idiomas();
            this.titulo = primerResultado.titulo();
        } else {
            System.out.println("NO FUNCIONOOOO");
        }
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
    }

    public Integer getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(Integer numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "Id=" + id +
                ", titulo='" + titulo + '\'' +
                ", autores=" + autores +
                ", idiomas=" + idiomas +
                ", numeroDeDescargas=" + numeroDeDescargas +
                '}';
    }
}
