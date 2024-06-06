package com.aluracursos.Biblioteca.principal;

import com.aluracursos.Biblioteca.model.Datos;
import com.aluracursos.Biblioteca.model.DatosLibro;
//import com.aluracursos.Biblioteca.model.Libro;
//import com.aluracursos.Biblioteca.repository.LibroRepository;
import com.aluracursos.Biblioteca.service.ConsumoAPI;
import com.aluracursos.Biblioteca.service.ConvierteDatos;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/";
    private ConvierteDatos conversor = new ConvierteDatos();
//    private LibroRepository repositorio;
//
//    public Principal(LibroRepository repository) {
//        this.repositorio = repository;
//    }

    public void mostrarMenu(){
        var opcion = -10;
        while (opcion != 0){
            var menu = """
                    1 - Buscar libro 
                                  
                    0 - Salir
                    
                    """;

            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();
            switch (opcion){
                case 1:
                    System.out.println("Ingrese el nombre del libro que desea buscar:");
                    var titulo = teclado.nextLine();
                    var json = consumoApi.obtenerDatos(URL_BASE+"?search="+titulo.replace(" ", "+"));
                    var buscarTitulo = conversor.obtenerDatos(json, Datos.class);
                    Optional<DatosLibro> libroBuscado = buscarTitulo.resultados().stream()
                            .filter(l -> l.titulo().toUpperCase().contains(titulo.toUpperCase()))
                            .findFirst();
                    if (libroBuscado.isPresent()){
                        System.out.println("Libro encontrado");
                        System.out.println(libroBuscado.get());
                    } else {
                        System.out.println("No hubo coincidencias");
                    }
                    break;
                default:
                    System.out.println("opcion no reconocida");
            }
        }

    }


//    private DatosLibro getDatosLibro() {
//
//    }
}
