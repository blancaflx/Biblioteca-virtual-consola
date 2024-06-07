package com.aluracursos.Biblioteca.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
@Entity
@Table(name = "autores")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("name")
    private String nombre;

    @JsonProperty("birth_year")
    @Column(name = "anio_nacimiento")
    private Integer añoNacimiento;

    @JsonProperty("death_year")
    @Column(name = "anio_muerte")
    private Integer añoMuerte;

    public Autor() {
    }

    public Autor(DatosAutor datosAutor) {
        if (datosAutor != null) {
            this.nombre = datosAutor.getNombre();
            this.añoNacimiento = datosAutor.getAñoNacimiento();
            this.añoMuerte = datosAutor.getAñoMuerte();
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getAñoNacimiento() {
        return añoNacimiento;
    }

    public void setAñoNacimiento(Integer añoNacimiento) {
        this.añoNacimiento = añoNacimiento;
    }

    public Integer getAñoMuerte() {
        return añoMuerte;
    }

    public void setAñoMuerte(Integer añoMuerte) {
        this.añoMuerte = añoMuerte;
    }
}