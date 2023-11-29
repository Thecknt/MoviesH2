package com.cristian.peliculas.controllers;

import com.cristian.peliculas.services.IArchivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ArchivoController {
    @Autowired
    private IArchivoService archivoService;

    @GetMapping("/archivo")
    public ResponseEntity<Resource> get(@RequestParam("n") String archivo){
        return archivoService.get(archivo);
    }
}
