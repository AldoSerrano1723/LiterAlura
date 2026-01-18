package com.aldocurso.LiterAlura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;

import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "libros")
public class Libro {
    //ATRIBUTOS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String lenguaje;
    private Double numeroDeDescargas;

    @ManyToOne
    private Autor autor;

    //CONSTRUCTOR
    public Libro(DatosLibro datosLibro){
        this.titulo = datosLibro.titulo();

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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
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
                Idioma: %s
                Numero de descargas: %.1f
                -------------------------
                """.formatted(titulo, lenguaje, numeroDeDescargas);
    }
}
