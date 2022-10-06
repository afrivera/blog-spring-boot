package com.afrivera.blog.dto;

import com.afrivera.blog.entity.Comentario;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

public class ComentarioDto {

    private Long id;

    @NotEmpty
    @Size(min = 5, message = "El nombre debe de contener más de 2 caracteres")
    private String nombre;

    @NotEmpty(message = "El email es requerido")
    @Email
    private String email;

    @NotEmpty
    @Size(min = 10, message = "El cuerpo debe contener más de 10 caracteres")
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
