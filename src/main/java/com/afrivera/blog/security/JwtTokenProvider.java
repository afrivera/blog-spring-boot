package com.afrivera.blog.security;

import com.afrivera.blog.exceptions.BlogAppException;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {
    @Value("${app.jwt-secret}")
    private String jwtSecret;

    @Value("${app.jwt-expiration-milliseconds}")
    private int jwtExpiration;

    // metodo para generar token
    public String generarToken(Authentication authentication){
        String username = authentication.getName();
        Date fechaActual = new Date();
        Date fechaExpiracion = new Date(fechaActual.getTime() + jwtExpiration);

        String token = Jwts.builder().setSubject(username).setIssuedAt(new Date()).setExpiration(fechaExpiracion)
                .signWith(SignatureAlgorithm.HS512, jwtSecret).compact();

        return token;
    }

    // metodo para obtener el username del token
    public String obtenerUsernameDelJWT(String token){
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    // valida el token recibido
    public boolean validarToken(String token){
        try{
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        }catch (SignatureException e){
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "Firma JWT no válida");
        } catch (MalformedJwtException e){
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "Token JWT no válida");
        } catch (ExpiredJwtException e){
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "Token JWT caducado");
        } catch (UnsupportedJwtException e){
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "Token JWT no compatible");
        } catch (IllegalArgumentException e ){
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "La cadena JWT está vacía");
        }
    }
}
