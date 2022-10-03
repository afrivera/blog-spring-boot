package com.afrivera.blog.controller;

import com.afrivera.blog.dto.PublicacionDto;
import com.afrivera.blog.service.PublicacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/publicaciones")
public class PublicacionController {

    @Autowired
    private PublicacionService publicacionService;

    @PostMapping
    public ResponseEntity<PublicacionDto> savePublicacion(@RequestBody PublicacionDto publicacionDto){
        return new ResponseEntity<>(publicacionService.crearPublicacion(publicacionDto), HttpStatus.CREATED);
    }
}
