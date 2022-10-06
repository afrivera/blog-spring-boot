package com.afrivera.blog.dto;

import com.afrivera.blog.entity.Comentario;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

public class PublicacionDto {

    private Long id;

    @NotEmpty
    @Size(min = 2, message = "El titiulo de la publicacion debe tener al menos 2 caracteres")
    private String title;

    @NotEmpty
    @Size(min = 10, message = "La descripción de la publicacion debe tener al menos 10 caracteres")
    private String description;

    @NotEmpty
    private String content;
    private Set<Comentario> comentarios;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Set<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(Set<Comentario> comentarios) {
        this.comentarios = comentarios;
    }
}
