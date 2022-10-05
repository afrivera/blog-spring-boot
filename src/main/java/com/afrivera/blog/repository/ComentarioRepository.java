package com.afrivera.blog.repository;

import com.afrivera.blog.entity.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

    public List<Comentario> findByPublicacionId(long publicacionId);
}
