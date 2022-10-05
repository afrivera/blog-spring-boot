package com.afrivera.blog.service;

import com.afrivera.blog.dto.ComentarioDto;

import java.util.List;

public interface ComentarioService {

    public ComentarioDto crearComentario(long publicacionId, ComentarioDto comentarioDto);
    public List<ComentarioDto> obtenerComentarioPorPublicacionId(long publicacionId);
    public ComentarioDto obtenerComentarioPorId(Long publicacionId, Long comentarioId);
}
