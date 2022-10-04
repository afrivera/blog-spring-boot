package com.afrivera.blog.controller;

import com.afrivera.blog.dto.PublicacionDto;
import com.afrivera.blog.dto.PublicacionResponse;
import com.afrivera.blog.service.PublicacionService;
import com.afrivera.blog.util.AppConstantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/publicaciones")
public class PublicacionController {

    @Autowired
    private PublicacionService publicacionService;

    @GetMapping
    public PublicacionResponse listarPublicaciones(
            @RequestParam(value = "pageNumber", defaultValue = AppConstantes.NUMERO_DE_PAGINA_POR_DEFECTO, required = false) int numeroPagina,
            @RequestParam(value = "pageSize", defaultValue = AppConstantes.MEDIDA_DE_PAGINA_POR_DEFECTO, required = false) int medidaPagina,
            @RequestParam(value = "sortBy", defaultValue = AppConstantes.ORDENAR_POR_DEFECTO, required = false) String ordenarPor,
            @RequestParam(value = "sortDir", defaultValue = AppConstantes.ORDENAR_DIRECCION_POR_DEFECTO, required = false) String sortDir) {
        return publicacionService.obtenerTodasPublicaciones(numeroPagina, medidaPagina, ordenarPor, sortDir);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublicacionDto> obtenerPublicacionPorId(@PathVariable(name = "id") long id) {
        return ResponseEntity.ok(publicacionService.obtenerPublicacionPorId(id));
    }

    @PostMapping
    public ResponseEntity<PublicacionDto> savePublicacion(@RequestBody PublicacionDto publicacionDto) {
        return new ResponseEntity<>(publicacionService.crearPublicacion(publicacionDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PublicacionDto> actualizarPublicacion(@RequestBody PublicacionDto publicacionDto, @PathVariable(name = "id") long id) {
        PublicacionDto publicacionRespuesta = publicacionService.actualizarPublicacion(publicacionDto, id);

        return new ResponseEntity<>(publicacionRespuesta, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> eliminarPublicacion(@PathVariable(name = "id") long id) {
        publicacionService.eliminarPublicacion(id);
        return new ResponseEntity<String>("Publicacion Eliminada con exito", HttpStatus.OK);
    }
}
