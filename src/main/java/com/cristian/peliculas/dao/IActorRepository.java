package com.cristian.peliculas.dao;

import com.cristian.peliculas.entities.Actor;
import org.springframework.data.repository.CrudRepository;

public interface IActorRepository extends CrudRepository<Actor, Long> {

}
