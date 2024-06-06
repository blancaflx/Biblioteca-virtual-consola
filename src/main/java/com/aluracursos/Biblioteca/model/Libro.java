//package com.aluracursos.Biblioteca.model;
//
//import jakarta.persistence.*;
//
//import java.util.List;
//
//@Entity
//@Table(name = "libros")
//public class Libro {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long Id;
//    @Column(unique = true)
//    private String titulo;
//    @OneToMany
//    private List<DatosAutor> autores;
//    @ElementCollection
//    private List<String> idiomas;
//    private Double numero_de_descargas;
//
//    public Libro(){}
//
//   public Libro(DatosLibro datosLibro) {
//    this.numero_de_descargas = datosLibro.numeroDescargas();
//    this.idiomas = datosLibro.idiomas();
//    this.autores = datosLibro.autor();
//    this.titulo = datosLibro.titulo();
//   }
//
// public Long getId() {
//  return Id;
// }
//
// public void setId(Long id) {
//  Id = id;
// }
//
// public String getTitulo() {
//  return titulo;
// }
//
// public void setTitulo(String titulo) {
//  this.titulo = titulo;
// }
//
// public List<DatosAutor> getAutores() {
//  return autores;
// }
//
// public void setAutores(List<DatosAutor> autores) {
//  this.autores = autores;
// }
//
// public List<String> getIdiomas() {
//  return idiomas;
// }
//
// public void setIdiomas(List<String> idiomas) {
//  this.idiomas = idiomas;
// }
//
// public Double getNumero_de_descargas() {
//  return numero_de_descargas;
// }
//
// public void setNumero_de_descargas(Double numero_de_descargas) {
//  this.numero_de_descargas = numero_de_descargas;
// }
//
//    @Override
//    public String toString() {
//        return "Libro{" +
//                "Id=" + Id +
//                ", titulo='" + titulo + '\'' +
//                ", autores=" + autores +
//                ", idiomas=" + idiomas +
//                ", numero_de_descargas=" + numero_de_descargas +
//                '}';
//    }
//}
