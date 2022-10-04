package com.afrivera.blog.service;

import com.afrivera.blog.dto.PublicacionDto;

import java.util.List;

public interface PublicacionService {

    public PublicacionDto crearPublicacion(PublicacionDto publicacionDto);

    public List<PublicacionDto> obtenerTodasPublicaciones();

    public PublicacionDto obtenerPublicacionPorId(long id);
}
