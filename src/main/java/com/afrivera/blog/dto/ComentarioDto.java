package com.afrivera.blog.dto;

import com.afrivera.blog.entity.Comentario;

import java.util.Set;

public class ComentarioDto {

    private Long id;
    private String nombre;
    private String email;
    private String cuerpo;

    public ComentarioDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

}
