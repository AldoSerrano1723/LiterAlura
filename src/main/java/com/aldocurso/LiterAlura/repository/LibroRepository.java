package com.aldocurso.LiterAlura.repository;

import com.aldocurso.LiterAlura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro, Long> {

    @Query("SELECT l FROM Libro l WHERE LOWER(l.titulo) = LOWER(:nombreDeLibro)")
    Optional<Libro> buscarLibroPorTitulo(String nombreDeLibro);

    @Query("SELECT l FROM Libro l WHERE LOWER(l.lenguaje) = LOWER(:lenguaje)")
    List<Libro> obtenerLibrosPorIdioma(String lenguaje);
}
