package com.cristian.peliculas.services;

import com.cristian.peliculas.entities.Pelicula;

import java.util.List;

public interface IPeliculaService {

    public void save(Pelicula pelicula);
    public Pelicula findById(Long id);
    public List<Pelicula> findAll();
    public void delete(Long id);
}
