package com.afrivera.blog.service;

import com.afrivera.blog.dto.PublicacionDto;

import java.util.List;

public interface PublicacionService {

    public PublicacionDto crearPublicacion(PublicacionDto publicacionDto);

    public List<PublicacionDto> obtenerTodasPublicaciones(int numeroPagina, int medidaPagina);

    public PublicacionDto obtenerPublicacionPorId(long id);

    public PublicacionDto actualizarPublicacion( PublicacionDto publicacionDto, long id);

    public void eliminarPublicacion(long id);
}
