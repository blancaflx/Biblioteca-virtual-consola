# Biblioteca virtual (LiterAlura)

## Descripción
Este proyecto es una biblioteca virtual que permite buscar libros, autores y realizar varias operaciones relacionadas con ellos mediante interacciones por consola.

## Características
- Buscar libros por título desde una API externa.
- Mostrar todos los libros y autores almacenados en la base de datos.
- Buscar autores vivos por año.
- Mostrar la cantidad de libros por idioma.
- Mostrar los top 10 libros más descargados desde una API externa.

## Tecnologías utilizadas
- Java
- Spring Boot
- Hibernate
- H2 Database
- RestTemplate para consumir la API externa

## Dependencias

- Spring Boot Starter
- Spring Boot Starter Test
- Jackson Databind
- OpenAI GPT-3 Java
- Spring Boot Starter Data JPA
- PostgreSQL Driver

Puedes encontrar más detalles sobre estas dependencias en el archivo `pom.xml`.

## API de Libros

Se utilizó la API gratuita de libros de [Gutendex](https://gutendex.com/) para obtener información sobre libros.


## Licencia
©️Este proyecto es el desafio Practicando Spring Boot: Challenge Literalura de la formacion Java y Spring Boot G6 - ONE cuyo proposito es crea aplicaciones web Java modernas con Spring Boot. Aprender a programar desde acceder a la base de datos, con Postgres, MySQL y JPA, hasta publicar una API REST.
