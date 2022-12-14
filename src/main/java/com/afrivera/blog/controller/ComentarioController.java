package com.afrivera.blog.controller;

import com.afrivera.blog.dto.ComentarioDto;
import com.afrivera.blog.service.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/publicaciones/{publicacionId}/comentarios")
    public ResponseEntity<ComentarioDto> guardarComentario(
            @PathVariable(value = "publicacionId") long publicacionId,
            @Valid @RequestBody ComentarioDto comentarioDto
    ) {
        return new ResponseEntity<>(comentarioService.crearComentario(publicacionId, comentarioDto), HttpStatus.CREATED);
    }

    @GetMapping("/publicaciones/{publicacionId}/comentarios")
    public List<ComentarioDto> listarComentariosPorPublicacionId(@PathVariable(value = "publicacionId") Long publicacionId ){
        return comentarioService.obtenerComentarioPorPublicacionId(publicacionId);
    }

    @GetMapping("/publicaciones/{publicacionId}/comentarios/{comentarioId}")
    public ResponseEntity<ComentarioDto> obtenerComentarioById(
            @PathVariable(value = "publicacionId") long publicacionId,
            @PathVariable(value = "comentarioId") long comentarioId
    ){
        ComentarioDto comentarioDto = comentarioService.obtenerComentarioPorId(publicacionId, comentarioId);
        return new ResponseEntity<>(comentarioDto, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/publicaciones/{publicacionId}/comentarios/{comentarioId}")
    public ResponseEntity<ComentarioDto> actualizarComentario(
            @PathVariable(value = "publicacionId") long publicacionId,
            @PathVariable(value = "comentarioId") long comentarioId,
            @Valid @RequestBody ComentarioDto comentarioDto
    ){
        ComentarioDto comentarioActualizado = comentarioService.actualizarComentario(publicacionId, comentarioId, comentarioDto);

        return new ResponseEntity<>(comentarioActualizado, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("publicaciones/{publicacionId}/comentarios/{comentarioId}")
    public ResponseEntity<String> eliminarComentario(
            @PathVariable(value = "publicacionId") long publicacionId,
            @PathVariable(value = "comentarioId") long comentarioId
    ){
        comentarioService.eliminarComentario(publicacionId, comentarioId);
        return new ResponseEntity<>("Comentario eliminado con ??xito", HttpStatus.OK);
    }
}
