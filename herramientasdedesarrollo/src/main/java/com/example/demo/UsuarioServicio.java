package com.example.clinica;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServicio {

    private List<Usuario> usuarios = new ArrayList<>();
    private int siguienteId = 1;

    @PostConstruct
    public void init() {
        // Usuario administrador por defecto
        usuarios.add(new Usuario(siguienteId++, "Dan", "123", "Administrador"));
    }

    public Optional<Usuario> autenticar(String nombre, String contrasena, String tipoUsuario) {
        return usuarios.stream()
                .filter(u -> u.getNombre().equalsIgnoreCase(nombre)
                        && u.getContrasena().equals(contrasena)
                        && u.getTipoUsuario().equalsIgnoreCase(tipoUsuario))
                .findFirst();
    }

    public Optional<Usuario> autenticarAdministrador(String nombre, String contrasena) {
        return usuarios.stream()
                .filter(u -> u.getNombre().equalsIgnoreCase(nombre)
                        && u.getContrasena().equals(contrasena)
                        && u.getTipoUsuario().equalsIgnoreCase("Administrador"))
                .findFirst();
    }

    public void agregarUsuario(Usuario usuario) {
        usuario.setId(siguienteId++);
        usuarios.add(usuario);
    }

    public List<Usuario> obtenerTodos() {
        return usuarios;
        
        
    }
}


