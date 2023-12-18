package com.cristian.peliculas.dao;

import com.cristian.peliculas.entities.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPeliculaRepository extends JpaRepository<Pelicula, Long> {

}
