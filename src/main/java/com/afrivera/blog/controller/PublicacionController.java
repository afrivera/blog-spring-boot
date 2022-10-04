package com.afrivera.blog.controller;

import com.afrivera.blog.dto.PublicacionDto;
import com.afrivera.blog.service.PublicacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/publicaciones")
public class PublicacionController {

    @Autowired
    private PublicacionService publicacionService;

    @GetMapping
    public List<PublicacionDto> listarPublicaciones(){
        return publicacionService.obtenerTodasPublicaciones();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublicacionDto> obtenerPublicacionPorId(@PathVariable(name = "id") long id){
        return ResponseEntity.ok(publicacionService.obtenerPublicacionPorId(id));
    }

    @PostMapping
    public ResponseEntity<PublicacionDto> savePublicacion(@RequestBody PublicacionDto publicacionDto){
        return new ResponseEntity<>(publicacionService.crearPublicacion(publicacionDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PublicacionDto> actualizarPublicacion(@RequestBody PublicacionDto publicacionDto, @PathVariable(name = "id") long id){
        PublicacionDto publicacionRespuesta = publicacionService.actualizarPublicacion(publicacionDto, id);

        return new ResponseEntity<>(publicacionRespuesta, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> eliminarPublicacion(@PathVariable(name = "id") long id){
        publicacionService.eliminarPublicacion(id);
        return new ResponseEntity<String>("Publicacion Eliminada con exito", HttpStatus.OK);
    }
}
