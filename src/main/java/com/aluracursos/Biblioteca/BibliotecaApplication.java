package com.aluracursos.Biblioteca;

import com.aluracursos.Biblioteca.principal.Principal;
import com.aluracursos.Biblioteca.repository.LibroRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BibliotecaApplication implements CommandLineRunner {
	private LibroRepository repository;
	public static void main(String[] args) {
		SpringApplication.run(BibliotecaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(repository);
		principal.mostrarMenu();

	}
}
