package com.aldocurso.LiterAlura.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public class Autor {
    //ATRIBUTOS
    private String nombre;
    private Integer fechaDeNacimiento;
    private Integer fechaDeMuerte;

    //CONSTRUCTOR
    public Autor(DatosAutor datosAutor){
        this.nombre = datosAutor.nombre();
        this.fechaDeNacimiento = datosAutor.fechaDeNacimiento();
        this.fechaDeMuerte = datosAutor.fechaDeMuerte();
    }

    //GETTERTS Y SETTERS
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(Integer fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public Integer getFechaDeMuerte() {
        return fechaDeMuerte;
    }

    public void setFechaDeMuerte(Integer fechaDeMuerte) {
        this.fechaDeMuerte = fechaDeMuerte;
    }

    //METODOS

    @Override
    public String toString() {
        return """
                ----- AUTOR -----
                Nombre : %s
                Año de nacimiento: %d
                Añno de muerte: %d
                """.formatted(nombre, fechaDeNacimiento, fechaDeMuerte);
    }
}
