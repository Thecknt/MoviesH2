package com.cristian.peliculas.services;

import com.cristian.peliculas.entities.Actor;

import java.util.List;

public interface IActorService {
    public List<Actor> findAll();

    public List<Actor> findAllById(List<Long> ids);

}
