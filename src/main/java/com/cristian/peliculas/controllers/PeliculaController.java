package com.cristian.peliculas.controllers;

import com.cristian.peliculas.entities.Pelicula;
import com.cristian.peliculas.services.IGeneroService;
import com.cristian.peliculas.services.IPeliculaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PeliculaController {

    private IPeliculaService peliculaService;

    private IGeneroService generoService;

    public PeliculaController(IPeliculaService peliculaService, IGeneroService generoService) {
        this.peliculaService = peliculaService;
        this.generoService = generoService;
    }

    @GetMapping("/pelicula")
    public String crear(Model model) {
        Pelicula pelicula = new Pelicula();
        model.addAttribute("pelicula", pelicula);
        model.addAttribute("titulo", "Nueva Pelicula");
        return "pelicula";
    }

    @GetMapping("/pelicula/{id}")
    public String editar(@PathVariable(name = "id") Long id, Model model) {
        Pelicula pelicula = new Pelicula();
        model.addAttribute("pelicula", pelicula);
        model.addAttribute("generos", generoService.findAll());
        model.addAttribute("titulo", "Editar Pelicula");
        return "pelicula";
    }

    @PostMapping("/pelicula")
    public String guardar(Pelicula pelicula) {
        peliculaService.save(pelicula);
        return "redirect:home";
    }
@GetMapping({"/", "/home", "/index"})
    public String home(){
        return "home";
    }
}
