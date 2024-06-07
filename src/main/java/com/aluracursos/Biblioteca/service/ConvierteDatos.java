package com.aluracursos.Biblioteca.service;

import com.aluracursos.Biblioteca.model.Datos;
import com.aluracursos.Biblioteca.model.DatosLibro;
import com.aluracursos.Biblioteca.model.Libro;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvierteDatos implements IConvierteDatos{
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public <T> T obtenerDatos(String json, Class<T> clase) {
        try {
            System.out.println("JSON QUE ENTRA A OBTENER DATOS:"+json);
            System.out.println("JASON QUE SALE DE OBTENER DATOS"+objectMapper.readValue(json,clase));
            return objectMapper.readValue(json, clase);
        } catch (JsonProcessingException e) {
            // Agrega un mensaje de registro para identificar la causa del problema
            System.err.println("Error al deserializar el JSON: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }


}
