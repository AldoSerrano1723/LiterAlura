package com.aldocurso.LiterAlura.model;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.List;
import java.util.Optional;

public class Libro {
    //ATRIBUTOS
    private String titulo;
    private String nombreAutor;
    private String lenguaje;
    private Double numeroDeDescargas;

    //CONSTRUCTOR
    public Libro(DatosLibro datosLibro){
        this.titulo = datosLibro.titulo();

        Optional<DatosAutor> autorOptional = datosLibro.autor().stream().findFirst();
        if (autorOptional.isPresent()){
            var autor = autorOptional.get();
            this.nombreAutor = autor.nombre();
        } else {
            this.nombreAutor = "Autor desconocido";
        }

        Optional<String> lenguajeOptional = datosLibro.lenguaje().stream().findFirst();
        if (lenguajeOptional.isPresent()) {
            this.lenguaje = lenguajeOptional.get();
        } else {
            this.lenguaje = "Sin idioma";
        }

        this.numeroDeDescargas = datosLibro.numeroDeDescargas();
    }

    public Libro(){}

    //GETTER Y SETTERS

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getNombreAutor() {
        return nombreAutor;
    }

    public void setNombreAutor(String nombreAutor) {
        this.nombreAutor = nombreAutor;
    }

    public String getLenguaje() {
        return lenguaje;
    }

    public void setLenguaje(String lenguaje) {
        this.lenguaje = lenguaje;
    }

    public Double getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(Double numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }

    //METODOS

    @Override
    public String toString() {
        return """
                ----- LIBRO -----
                Titulo: %s
                Autor: %s
                Idioma: %s
                Numero de descargas: %.1f
                -------------------------
                """.formatted(titulo, nombreAutor, lenguaje, numeroDeDescargas);
    }
}
