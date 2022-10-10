package com.afrivera.blog.controller;

import com.afrivera.blog.dto.LoginDto;
import com.afrivera.blog.dto.RegistroDto;
import com.afrivera.blog.entity.Role;
import com.afrivera.blog.entity.Usuario;
import com.afrivera.blog.repository.RoleRepository;
import com.afrivera.blog.repository.UsuarioRepository;
import com.afrivera.blog.security.JwtAuthResponseDTO;
import com.afrivera.blog.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponseDTO> authenticateUser(@RequestBody LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // obtenemos el token del provider
        String token = jwtTokenProvider.generarToken(authentication);
        return ResponseEntity.ok(new JwtAuthResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registrarUsuario(@RequestBody RegistroDto registroDto){
        if (usuarioRepository.existsByUsername(registroDto.getUsername())) {
            return new ResponseEntity<>("Ese nombre ya existe", HttpStatus.BAD_REQUEST);
        }
        if (usuarioRepository.existsByEmail(registroDto.getEmail())) {
            return new ResponseEntity<>("Ese email de usuario ya existe", HttpStatus.BAD_REQUEST);
        }
        Usuario usuario = new Usuario();
        usuario.setNombre(registroDto.getNombre());
        usuario.setUsername(registroDto.getUsername());
        usuario.setEmail(registroDto.getEmail());
        usuario.setPassword(passwordEncoder.encode(registroDto.getPassword()));

        Role roles = roleRepository.findByNombre("ROLE_ADMIN").get();
        usuario.setRoles(Collections.singleton(roles));

        usuarioRepository.save(usuario);

        return new ResponseEntity<>("Usuario registrado exitosamente", HttpStatus.CREATED);
    }
}
