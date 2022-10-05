package com.afrivera.blog.controller;

import com.afrivera.blog.dto.ComentarioDto;
import com.afrivera.blog.service.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

    @PostMapping("/publicaciones/{publicacionId}/comentarios")
    public ResponseEntity<ComentarioDto> guardarComentario(
            @PathVariable(value = "publicacionId") long publicacionId,
            @RequestBody ComentarioDto comentarioDto
    ) {
        return new ResponseEntity<>(comentarioService.crearComentario(publicacionId, comentarioDto), HttpStatus.CREATED);
    }

    @GetMapping("/publicaciones/{publicacionId}/comentarios")
    public List<ComentarioDto> listarComentariosPorPublicacionId(@PathVariable(value = "publicacionId") long publicacionId ){
        return comentarioService.obtenerComentarioPorPublicacionId(publicacionId);
    }
}