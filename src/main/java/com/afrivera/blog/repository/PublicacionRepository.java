package com.afrivera.blog.repository;

import com.afrivera.blog.entity.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublicacionRepository extends JpaRepository<Publicacion, Long> {
}
