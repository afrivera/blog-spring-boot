package com.afrivera.blog.service;

import com.afrivera.blog.dto.ComentarioDto;
import com.afrivera.blog.entity.Comentario;
import com.afrivera.blog.entity.Publicacion;
import com.afrivera.blog.exceptions.BlogAppException;
import com.afrivera.blog.exceptions.ResourceNotFoundException;
import com.afrivera.blog.repository.ComentarioRepository;
import com.afrivera.blog.repository.PublicacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<ComentarioDto> obtenerComentarioPorPublicacionId(long publicacionId) {
        List<Comentario> comentarios = comentarioRepository.findByPublicacionId(publicacionId);

        return comentarios.stream().map(comentario -> mapearDto(comentario)).collect(Collectors.toList());
    }

    @Override
    public ComentarioDto obtenerComentarioPorId(Long publicacionId, Long comentarioId) {
        Publicacion publicacion = publicacionRepository
                .findById(publicacionId)
                .orElseThrow(()->new ResourceNotFoundException("Publicacion", "id", publicacionId));

        Comentario comentario = comentarioRepository.findById(comentarioId)
                .orElseThrow(()-> new ResourceNotFoundException("Comentario", "id", comentarioId));

        if (!comentario.getPublicacion().getId().equals(publicacion.getId())) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "El comentario no pertenece a la publicacion");
        }

        return mapearDto(comentario);
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
