package com.aluracursos.Biblioteca.model;

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

    @OneToMany(mappedBy = "libro")
    private List<Autor> autores;

    @ElementCollection
    private List<String> idiomas;

    private Double numeroDeDescargas;

    public Libro() {}

    public Libro(DatosLibro datosLibro) {
        this.numeroDeDescargas = datosLibro.numeroDescargas();
        this.idiomas = datosLibro.idiomas();
        this.titulo = datosLibro.titulo();
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

    public Double getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(Double numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "Id=" + id +
                ", titulo='" + titulo + '\'' +
                ", autores=" + autores +
                ", idiomas=" + idiomas +
                ", numero_de_descargas=" + numeroDeDescargas +
                '}';
    }
}
