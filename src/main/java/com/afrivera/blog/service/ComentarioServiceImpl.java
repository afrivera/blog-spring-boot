package com.afrivera.blog.service;

import com.afrivera.blog.dto.ComentarioDto;
import com.afrivera.blog.entity.Comentario;
import com.afrivera.blog.entity.Publicacion;
import com.afrivera.blog.exceptions.ResourceNotFoundException;
import com.afrivera.blog.repository.ComentarioRepository;
import com.afrivera.blog.repository.PublicacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComentarioServiceImpl implements ComentarioService{

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private PublicacionRepository publicacionRepository;

    @Override
    public ComentarioDto crearComentario(long publicacionId, ComentarioDto comentarioDto) {
        Comentario comentario = mapearEntidad(comentarioDto);

        Publicacion publicacion = publicacionRepository
                .findById(publicacionId)
                .orElseThrow(()-> new ResourceNotFoundException("Publicación", "id", publicacionId));

        comentario.setPublicacion(publicacion);
        Comentario nuevoComentario = comentarioRepository.save(comentario);

        return mapearDto(nuevoComentario);
    }

    private ComentarioDto mapearDto(Comentario comentario){
        ComentarioDto comentarioDto = new ComentarioDto();
        comentarioDto.setId(comentario.getId());
        comentarioDto.setNombre(comentario.getNombre());
        comentarioDto.setEmail(comentario.getEmail());
        comentarioDto.setCuerpo(comentario.getCuerpo());

        return comentarioDto;
    }

    private Comentario mapearEntidad(ComentarioDto comentarioDto){
        Comentario comentario = new Comentario();
        comentario.setId(comentarioDto.getId());
        comentario.setNombre(comentarioDto.getNombre());
        comentario.setEmail(comentarioDto.getEmail());
        comentario.setCuerpo(comentarioDto.getCuerpo());

        return comentario;
    }
}
