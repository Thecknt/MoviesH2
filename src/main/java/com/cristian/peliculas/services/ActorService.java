package com.cristian.peliculas.services;

import com.cristian.peliculas.dao.IActorRepository;
import com.cristian.peliculas.entities.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorService implements IActorService {

    @Autowired
    private IActorRepository actorRepository;

    public ActorService(IActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    @Override
    public List<Actor> findAll() {
        return (List<Actor>) actorRepository.findAll();
    }

    @Override
    public List<Actor> findAllById(List<Long> ids) {
        return (List<Actor>) actorRepository.findAllById(ids);
    }
}
