package com.afrivera.blog.service;

import com.afrivera.blog.dto.PublicacionDto;
import com.afrivera.blog.dto.PublicacionResponse;


public interface PublicacionService {

    public PublicacionDto crearPublicacion(PublicacionDto publicacionDto);

    public PublicacionResponse obtenerTodasPublicaciones(int numeroPagina, int medidaPagina, String ordenarPor, String sortDir);

    public PublicacionDto obtenerPublicacionPorId(long id);

    public PublicacionDto actualizarPublicacion( PublicacionDto publicacionDto, long id);

    public void eliminarPublicacion(long id);
}
