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
            inicializarDesdeDatos(datos);
        } else {
            throw new IllegalArgumentException("Los datos de entrada no son válidos.");
        }
    }

    private void inicializarDesdeDatos(Datos datos) {
        DatosLibro primerResultado = datos.resultados().get(0);
        this.numeroDescargas = primerResultado.numeroDescargas();
        this.idiomas = primerResultado.idiomas();
        this.titulo = primerResultado.titulo();
        this.autores = primerResultado.autor();
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
        sb.append("Libro {\n");
        sb.append("  Título: '").append(titulo).append("'\n");
        sb.append("  Autores: ").append(autoresToString()).append("\n");
        sb.append("  Idiomas: ").append(idiomasToString()).append("\n");
        sb.append("  Número de descargas: ").append(numeroDescargas).append("\n");
        sb.append("}");
        return sb.toString();
    }
    private String autoresToString() {
        StringBuilder sb = new StringBuilder();
        if (autores != null && !autores.isEmpty()) {
            for (Autor autor : autores) {
                sb.append("\n    - Nombre: ").append(autor.getNombre());
                sb.append(", Año de nacimiento: ").append(autor.getAñoNacimiento());
                sb.append(", Año de muerte: ").append(autor.getAñoMuerte() != null ? autor.getAñoMuerte() : "Vivo");
            }
        }
        return sb.toString();
    }

    private String idiomasToString() {
        return idiomas != null ? idiomas.toString() : "N/A";
    }
}
