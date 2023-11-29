package com.cristian.peliculas.controllers;

import com.cristian.peliculas.entities.Actor;
import com.cristian.peliculas.entities.Pelicula;
import com.cristian.peliculas.services.IActorService;
import com.cristian.peliculas.services.IArchivoService;
import com.cristian.peliculas.services.IGeneroService;
import com.cristian.peliculas.services.IPeliculaService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class PeliculaController {

    private IPeliculaService peliculaService;
    private IGeneroService generoService;
    private IActorService actorService;
    private IArchivoService archivoService;

    public PeliculaController(IPeliculaService peliculaService, IGeneroService generoService, IActorService actorService,IArchivoService archivoService) {
        this.peliculaService = peliculaService;
        this.generoService = generoService;
        this.actorService = actorService;
        this.archivoService = archivoService;
    }

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
    public String guardar(@Valid Pelicula pelicula, BindingResult bindingResult, @ModelAttribute(name = "ids") String ids, Model model, @RequestParam("archivo")MultipartFile imagen){

        //BindingResult es donde se guardados las variaciones del form
        //Si hay un error en el form lo envio a cargar nuevamente
        if (bindingResult.hasErrors()) {
            model.addAttribute("generos", generoService.findAll());
            model.addAttribute("actores", actorService.findAll());
            return "pelicula";
        }

        if (!imagen.isEmpty()){
            String archivo = pelicula.getNombre() + getExtension(imagen.getOriginalFilename());
            try {
                archivoService.guardar(archivo, imagen.getInputStream());
                pelicula.setImagen(archivo);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            pelicula.setImagen("default.jpeg");
        }
        List<Long> idsProtagonistas = Arrays.stream(ids.split(",")).map(Long::parseLong).collect(Collectors.toList());
        List<Actor> protagonistas = actorService.findAllById(idsProtagonistas);
        pelicula.setProtagonistas(protagonistas);
        peliculaService.save(pelicula);
        return "redirect:home";
    }

    private String getExtension(String archivo){
        return archivo.substring(archivo.lastIndexOf("."));
    }

    @GetMapping({"/", "/home", "/index"})
    public String home(Model model) {
        model.addAttribute("peliculas", peliculaService.findAll());
        /*model.addAttribute("msj", "Catalogo Actualizado");
        model.addAttribute("tipoMsj", "success");*/
        return "home";
    }
}
