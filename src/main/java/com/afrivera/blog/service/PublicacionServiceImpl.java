package com.afrivera.blog.service;

import com.afrivera.blog.dto.PublicacionDto;
import com.afrivera.blog.dto.PublicacionResponse;
import com.afrivera.blog.entity.Publicacion;
import com.afrivera.blog.exceptions.ResourceNotFoundException;
import com.afrivera.blog.repository.PublicacionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PublicacionServiceImpl implements PublicacionService {

    @Autowired
    private ModelMapper modelMapper;
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
    public PublicacionResponse obtenerTodasPublicaciones(int numeroPagina, int medidaPagina, String ordenarPor, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(ordenarPor).ascending():Sort.by(ordenarPor).descending();

        Pageable pageable = PageRequest.of(numeroPagina, medidaPagina, sort);
        Page<Publicacion> publicaciones = publicacionRepository.findAll(pageable);
        List<Publicacion> listaPublicaciones = publicaciones.getContent();

        List<PublicacionDto> contenido =  listaPublicaciones.stream().map(publicacion -> mapearDTO(publicacion)).collect(Collectors.toList());

        PublicacionResponse publicacionResponse = new PublicacionResponse();
        publicacionResponse.setContenido(contenido);
        publicacionResponse.setNumeroPagina(publicaciones.getNumber());
        publicacionResponse.setMedidaPagina(publicaciones.getSize());
        publicacionResponse.setTotalElementos(publicaciones.getTotalElements());
        publicacionResponse.setTotalPaginas(publicaciones.getTotalPages());
        publicacionResponse.setUltima(publicaciones.isLast());

        return publicacionResponse;

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
        PublicacionDto publicacionDto = modelMapper.map(publicacion, PublicacionDto.class);
        return publicacionDto;
    }

    // convierte de DTO a Entidad
    private Publicacion mapearEntidad(PublicacionDto publicacionDto) {
        Publicacion publicacion = modelMapper.map(publicacionDto, Publicacion.class);
        return publicacion;
    }

}
