package com.afrivera.blog.service;

import com.afrivera.blog.dto.ComentarioDto;

public interface ComentarioService {

    public ComentarioDto crearComentario(long publicacionId, ComentarioDto comentarioDto);
}
