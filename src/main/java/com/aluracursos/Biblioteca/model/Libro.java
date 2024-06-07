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

    private String titulo;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "libro_id")
    private List<Autor> autores;


    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "idiomas", joinColumns = @JoinColumn(name = "libro_id"))
    @Column(name = "idioma")
    private List<String> idiomas;


    @Column(name = "numero_descargas")
    private Integer numeroDescargas;

    public Libro() {
    }

    public Libro(Datos datos) {
        if (datos != null && datos.resultados() != null && !datos.resultados().isEmpty()) {
            // Supongamos que solo queremos utilizar el primer resultado de la lista
            DatosLibro primerResultado = datos.resultados().get(0);

            // Asignar los valores del primer resultado a los atributos del libro
            this.numeroDescargas = primerResultado.numeroDescargas();
            this.idiomas = primerResultado.idiomas();
            this.titulo = primerResultado.titulo();
            this.autores = primerResultado.autor();
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

    public Integer getNumeroDescargas() {
        return numeroDescargas;
    }

    public void setNumeroDescargas(Integer numeroDescargas) {
        this.numeroDescargas = numeroDescargas;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("titulo='").append(titulo).append('\'');
        sb.append(", autores=[");
        for (Autor autor : autores) {
            sb.append("{nombre=").append(autor.getNombre());
            sb.append(", añoNacimiento=").append(autor.getAñoNacimiento());
            sb.append(", añoMuerte=").append(autor.getAñoMuerte()).append("}, ");
        }
        sb.append("]");
        sb.append(", idiomas=").append(idiomas);
        sb.append(", numeroDescargas=").append(numeroDescargas);
        sb.append('}');
        return sb.toString();
    }

}