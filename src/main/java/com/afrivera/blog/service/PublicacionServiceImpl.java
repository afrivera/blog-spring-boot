package com.afrivera.blog.service;

import com.afrivera.blog.dto.PublicacionDto;
import com.afrivera.blog.entity.Publicacion;
import com.afrivera.blog.exceptions.ResourceNotFoundException;
import com.afrivera.blog.repository.PublicacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PublicacionServiceImpl implements PublicacionService {

    @Autowired
    private PublicacionRepository publicacionRepository;

    @Override
    public PublicacionDto crearPublicacion(PublicacionDto publicacionDto) {
        Publicacion publicacion = mapearEntidad(publicacionDto);

        Publicacion nuevaPublicacion = publicacionRepository.save(publicacion);

        PublicacionDto publicacionResponse = mapearDTO(nuevaPublicacion);

        return publicacionResponse;
    }

    @Override
    public List<PublicacionDto> obtenerTodasPublicaciones(int numeroPagina, int medidaPagina) {
        Pageable pageable = PageRequest.of(numeroPagina, medidaPagina);
        Page<Publicacion> publicaciones = publicacionRepository.findAll(pageable);
        List<Publicacion> listaPublicaciones = publicaciones.getContent();

        return listaPublicaciones.stream().map(publicacion -> mapearDTO(publicacion)).collect(Collectors.toList());
    }

    @Override
    public PublicacionDto obtenerPublicacionPorId(long id) {
        Publicacion publicacion = publicacionRepository
                .findById(id).orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", id));
        return mapearDTO(publicacion);
    }

    @Override
    public PublicacionDto actualizarPublicacion(PublicacionDto publicacionDto, long id) {
        Publicacion publicacion = publicacionRepository
                .findById(id).orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", id));

        publicacion.setTitle(publicacionDto.getTitle());
        publicacion.setDescription(publicacionDto.getDescription());
        publicacion.setContent(publicacionDto.getContent());

        Publicacion publicacionActualizada = publicacionRepository.save(publicacion);
        return mapearDTO(publicacionActualizada);
    }

    @Override
    public void eliminarPublicacion(long id) {
        Publicacion publicacion = publicacionRepository
                .findById(id).orElseThrow(()-> new ResourceNotFoundException("Publicacion", "id", id));

        publicacionRepository.delete(publicacion);
    }

    // Convierte entidad a DTO
    private PublicacionDto mapearDTO(Publicacion publicacion) {
        PublicacionDto publicacionDto = new PublicacionDto();

        publicacionDto.setId(publicacion.getId());
        publicacionDto.setTitle(publicacion.getTitle());
        publicacionDto.setDescription(publicacion.getDescription());
        publicacionDto.setContent(publicacion.getContent());

        return publicacionDto;
    }

    // convierte de DTO a Entidad
    private Publicacion mapearEntidad(PublicacionDto publicacionDto) {
        Publicacion publicacion = new Publicacion();

        publicacion.setId(publicacionDto.getId());
        publicacion.setTitle(publicacionDto.getTitle());
        publicacion.setDescription(publicacionDto.getDescription());
        publicacion.setContent(publicacionDto.getContent());

        return publicacion;
    }

}
