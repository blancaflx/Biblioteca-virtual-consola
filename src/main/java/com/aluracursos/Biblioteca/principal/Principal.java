package com.aluracursos.Biblioteca.principal;

import com.aluracursos.Biblioteca.model.Autor;
import com.aluracursos.Biblioteca.model.Datos;
import com.aluracursos.Biblioteca.model.DatosLibro;
import com.aluracursos.Biblioteca.model.Libro;
import com.aluracursos.Biblioteca.repository.AutorRepository;
import com.aluracursos.Biblioteca.repository.LibroRepository;
import com.aluracursos.Biblioteca.service.ConsumoAPI;
import com.aluracursos.Biblioteca.service.ConvierteDatos;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/";
    private ConvierteDatos conversor = new ConvierteDatos();
    private LibroRepository repositorio;
    private AutorRepository repositorioAutores;
    private List<Libro> libros;

    public Principal(LibroRepository repositorio, AutorRepository repositorioAutores) {
        this.repositorio = repositorio;
        this.repositorioAutores = repositorioAutores;
    }

    public void mostrarMenu(){
        var opcion = -10;
        while (opcion != 0){
            var menu = """
                    1 - Buscar libro por titulo
                    2 - Lista de todos los libros buscados
                    3 - Lista de autores buscados
                    4 - Buscar autores vivos por anio
                                  
                    0 - Salir
                    
                    """;

            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();
            switch (opcion){
                case 1:
                    buscarLibroAPI();
                    break;
                case 2:
                    mostrarLibrosBuscados();
                    break;
                case 3:
                    mostrarAutoresBuscados();
                    break;
                case 4:

                    break;
                default:
                    System.out.println("opcion no reconocida");
            }
        }

    }




    private void buscarLibroAPI(){
        Datos datos = getDatosLibro();
        Libro libro = new Libro(datos);
        repositorio.save(libro);
//        System.out.println(datos);

    }

    private Datos getDatosLibro(){
        System.out.println("Escribe el nombre del libro que deseas buscar");
        var nombreLibro = teclado.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE+"?search="+nombreLibro.replace(" ", "+"));
        System.out.println("DATOS JSON:"+json);
        Datos datos = conversor.obtenerDatos(json, Datos.class);
        System.out.println("DATOS CONVERTIDOS:"+datos);
        return datos;
    }

    private void mostrarLibrosBuscados() {
        List<Libro> libros = repositorio.findAll();
        for (Libro libro : libros) {
            System.out.println(libro);
        }
    }

    public void mostrarAutoresBuscados() {
        List<Autor> autores = repositorioAutores.findAll();
        if (autores.isEmpty()) {
            System.out.println("No hay autores en la base de datos.");
        } else {
            System.out.println("Lista de autores:");
            for (Autor autor : autores) {
                System.out.println("Nombre: " + autor.getNombre());
                System.out.println("Año de nacimiento: " + autor.getAñoNacimiento());
                System.out.println("Año de muerte: " + autor.getAñoMuerte());
                System.out.println();
            }
        }
    }





}
