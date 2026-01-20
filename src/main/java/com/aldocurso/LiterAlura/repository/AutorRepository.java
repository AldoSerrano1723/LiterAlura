package com.aldocurso.LiterAlura.repository;

import com.aldocurso.LiterAlura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface AutorRepository extends JpaRepository<Autor, Long>{

    @Query("SELECT a FROM Autor a WHERE LOWER(a.nombre) = lower(:nombreDeAutor)")
    Optional<Autor> buscarAutorPorNombre(String nombreDeAutor);

    @Query("SELECT a FROM Autor a WHERE a.fechaDeNacimiento <= :anio AND (a.fechaDeMuerte IS NULL OR a.fechaDeMuerte >= :anio)")
    List<Autor> obtenerAutoresVivosEnAnio(Integer anio);
}
