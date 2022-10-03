package com.afrivera.blog.service;

import com.afrivera.blog.dto.PublicacionDto;
import com.afrivera.blog.entity.Publicacion;
import com.afrivera.blog.repository.PublicacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublicacionServiceImpl implements PublicacionService{

    @Autowired
    private PublicacionRepository publicacionRepository;

    @Override
    public PublicacionDto crearPublicacion(PublicacionDto publicacionDto) {
        // convertir de DTO a entidad
        Publicacion publicacion = new Publicacion();
        publicacion.setTitle(publicacionDto.getTitle());
        publicacion.setDescription(publicacionDto.getDescription());
        publicacion.setContent(publicacionDto.getContent());

        Publicacion newPublicacion = publicacionRepository.save(publicacion);

        // convertimos de entidad a DTO
        PublicacionDto publicacionResponse = new PublicacionDto();
        publicacionResponse.setId(newPublicacion.getId());
        publicacionResponse.setTitle(newPublicacion.getTitle());
        publicacionResponse.setDescription(newPublicacion.getDescription());
        publicacionResponse.setContent(newPublicacion.getContent());

        return publicacionResponse;
    }
}
