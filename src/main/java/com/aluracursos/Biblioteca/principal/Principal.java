package com.aluracursos.Biblioteca.principal;

import com.aluracursos.Biblioteca.model.Autor;
import com.aluracursos.Biblioteca.model.Datos;
import com.aluracursos.Biblioteca.model.Libro;
import com.aluracursos.Biblioteca.repository.AutorRepository;
import com.aluracursos.Biblioteca.repository.LibroRepository;
import com.aluracursos.Biblioteca.service.ConsumoAPI;
import com.aluracursos.Biblioteca.service.ConvierteDatos;
import java.util.HashMap;
import java.util.Map;

import java.util.*;
import java.util.stream.Collectors;

import java.util.*;

public class Principal {
    private final Scanner teclado = new Scanner(System.in);
    private final ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/";
    private final ConvierteDatos conversor = new ConvierteDatos();
    private final LibroRepository repositorio;
    private final AutorRepository repositorioAutores;

    public Principal(LibroRepository repositorio, AutorRepository repositorioAutores) {
        this.repositorio = repositorio;
        this.repositorioAutores = repositorioAutores;
    }

    public void mostrarMenu() {
        int opcion;
        do {
            mostrarOpcionesMenu();
            opcion = leerEntero("Seleccione una opción: ");
            switch (opcion) {
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
                    mostrarAutoresVivosPorAnio();
                    break;
                case 5:
                    mostrarCantidadLibrosPorIdioma();
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 0);
        teclado.close(); // Cerrar el Scanner
    }

    private void mostrarOpcionesMenu() {
        System.out.println("""
                Menú:
                1 - Buscar libro por título
                2 - Lista de todos los libros buscados
                3 - Lista de autores buscados
                4 - Buscar autores vivos por año
                5 - Cantidad de libros por idioma
                0 - Salir
                """);
    }

    private void buscarLibroAPI() {
        Datos datos = getDatosLibro();
        if (datos != null) {
            Libro libro = new Libro(datos);
            repositorio.save(libro);
        }
    }

    private Datos getDatosLibro() {
        System.out.println("Escriba el nombre del libro que desea buscar: ");
        String nombreLibro = teclado.nextLine();
        String json = consumoApi.obtenerDatos(URL_BASE + "?search=" + nombreLibro.replace(" ", "+"));
        System.out.println("Datos JSON: " + json);
        return conversor.obtenerDatos(json, Datos.class);
    }

    private void mostrarLibrosBuscados() {
        repositorio.findAll().forEach(System.out::println);
    }

    private void mostrarAutoresBuscados() {
        List<Autor> autores = repositorioAutores.findAll();
        if (autores.isEmpty()) {
            System.out.println("No hay autores en la base de datos.");
        } else {
            System.out.println("Lista de autores:");
            autores.forEach(autor -> {
                System.out.println("Nombre: " + autor.getNombre());
                System.out.println("Año de nacimiento: " + autor.getAñoNacimiento());
                System.out.println("Año de muerte: " + (autor.getAñoMuerte() != null ? autor.getAñoMuerte() : "Vivo"));
                System.out.println();
            });
        }
    }

    private void mostrarAutoresVivosPorAnio() {
        int anio = leerEntero("Escriba el año: ");
        List<Autor> autoresVivos = repositorioAutores.buscarAutoresVivosPorAnio(anio);
        if (autoresVivos.isEmpty()) {
            System.out.println("No hay autores vivos para el año ingresado.");
        } else {
            System.out.println("Lista de autores vivos para el año " + anio + ":");
            autoresVivos.forEach(autor -> {
                System.out.println("Nombre: " + autor.getNombre());
                System.out.println("Año de nacimiento: " + autor.getAñoNacimiento());
                System.out.println("Año de muerte: " + (autor.getAñoMuerte() != null ? autor.getAñoMuerte() : "Vivo"));
                System.out.println();
            });
        }
    }

    private void mostrarCantidadLibrosPorIdioma() {
        Map<Integer, String> opcionesIdioma = Map.of(1, "en", 2, "fr");
        System.out.println("Seleccione el idioma:");
        opcionesIdioma.forEach((clave, valor) -> System.out.println(clave + " - " + (valor.equals("en") ? "Inglés" : "Francés")));
        int opcionIdioma = leerEntero("Escriba el número correspondiente al idioma: ");
        String idioma = opcionesIdioma.get(opcionIdioma);
        if (idioma != null) {
            Long cantidadLibros = repositorio.contarLibrosPorIdioma(idioma);
            System.out.println("Cantidad de libros en " + (idioma.equals("en") ? "Inglés" : "Francés") + ": " + cantidadLibros);
        } else {
            System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
        }
    }


    private int leerEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Integer.parseInt(teclado.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un número válido.");
            }
        }
    }
}
