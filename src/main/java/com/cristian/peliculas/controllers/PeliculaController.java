package com.cristian.peliculas.controllers;

import com.cristian.peliculas.entities.Actor;
import com.cristian.peliculas.entities.Pelicula;
import com.cristian.peliculas.services.IActorService;
import com.cristian.peliculas.services.IGeneroService;
import com.cristian.peliculas.services.IPeliculaService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class PeliculaController {

    private IPeliculaService peliculaService;

    private IGeneroService generoService;

    private IActorService actorService;

    public PeliculaController(IPeliculaService peliculaService, IGeneroService generoService, IActorService actorService) {
        this.peliculaService = peliculaService;
        this.generoService = generoService;
        this.actorService = actorService;
    }

    String pelicula = "pelicula";
    @GetMapping("/pelicula")
    public String crear(Model model) {
        Pelicula pelicula = new Pelicula();
        model.addAttribute("pelicula", pelicula);
        model.addAttribute("generos", generoService.findAll());
        model.addAttribute("actores", actorService.findAll());
        model.addAttribute("titulo", "Nueva Pelicula");
        return "pelicula";
    }

    @GetMapping("/pelicula/{id}")
    public String editar(@PathVariable(name = "id") Long id, Model model) {
        Pelicula pelicula = new Pelicula();
        model.addAttribute("pelicula", pelicula);
        model.addAttribute("generos", generoService.findAll());
        model.addAttribute("actores", actorService.findAll());
        model.addAttribute("titulo", "Editar Pelicula");
        return "pelicula";
    }

    @PostMapping("/pelicula")
    public String guardar(@Valid Pelicula pelicula, BindingResult bindingResult, @ModelAttribute(name = "ids") String ids) {

        //BindingResult es donde se guardados las variaciones del form
        //Si hay un error en el form lo envio a cargar nuevamente
        if (bindingResult.hasErrors()) {
            return "pelicula";
        }
        List<Long> idsProtagonistas = Arrays.stream(ids.split(",")).map(Long::parseLong).collect(Collectors.toList());
        List<Actor> protagonistas = actorService.findAllById(idsProtagonistas);
        pelicula.setProtagonistas(protagonistas);
        peliculaService.save(pelicula);
        return "redirect:home";
    }

    @GetMapping({"/", "/home", "/index"})
    public String home() {
        return "home";
    }
}
