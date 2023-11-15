package com.cristian.peliculas.dao;

import com.cristian.peliculas.entities.Pelicula;
import org.springframework.data.repository.CrudRepository;

public interface IPeliculaRepository extends CrudRepository<Pelicula, Long> {

}
