package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioServicio usuarioServicio;

    // Página login
    @GetMapping({"/", "/login"})
    public String mostrarLogin() {
        return "login";  // busca login.html en /templates
    }

    // Login API (JSON)
    @PostMapping("/api/login")
    @ResponseBody
    public ResponseEntity<?> login(@RequestBody Map<String, String> payload) {
        String nombre = payload.get("nombre");
        String contrasena = payload.get("contrasena");
        String tipoUsuario = payload.get("tipoUsuario");

        if (nombre == null || contrasena == null || tipoUsuario == null) {
            return ResponseEntity.badRequest().body(Map.of("mensaje", "Faltan campos obligatorios"));
        }

        if (tipoUsuario.equalsIgnoreCase("Invitado")) {
            return ResponseEntity.ok(Map.of("estado", "exito", "redirigir", "MenuInvitado.html"));
        }

        Optional<Usuario> usuarioOpt = usuarioServicio.autenticar(nombre, contrasena, tipoUsuario);
        if (usuarioOpt.isPresent()) {
            String pagina = switch (tipoUsuario.toLowerCase()) {
                case "médico", "medico" -> "MenuMedico.html";
                case "paciente" -> "MenuPaciente.html";
                case "administrador" -> "MenuAdmin.html";
                default -> "MenuInvitado.html";
            };
            return ResponseEntity.ok(Map.of("estado", "exito", "redirigir", pagina));
        } else {
            return ResponseEntity.status(401).body(Map.of("estado", "fallo", "mensaje", "Credenciales incorrectas"));
        }
    }

    // Registrar nuevo usuario (requiere admin)
    @PostMapping("/api/registrar")
    @ResponseBody
    public ResponseEntity<?> registrar(@RequestBody Map<String, String> payload) {
        String adminNombre = payload.get("adminNombre");
        String adminContrasena = payload.get("adminContrasena");
        String nuevoNombre = payload.get("nombre");
        String nuevaContrasena = payload.get("contrasena");
        String nuevoTipo = payload.get("tipoUsuario");

        if (adminNombre == null || adminContrasena == null || nuevoNombre == null || nuevaContrasena == null || nuevoTipo == null) {
            return ResponseEntity.badRequest().body(Map.of("mensaje", "Faltan campos obligatorios"));
        }

        Optional<Usuario> adminOpt = usuarioServicio.autenticarAdministrador(adminNombre, adminContrasena);
        if (adminOpt.isEmpty()) {
            return ResponseEntity.status(401).body(Map.of("mensaje", "Credenciales de administrador incorrectas"));
        }

        usuarioServicio.agregarUsuario(new Usuario(nuevoNombre, nuevaContrasena, nuevoTipo));
        return ResponseEntity.ok(Map.of("mensaje", "Usuario registrado correctamente"));
    }

    // Obtener lista usuarios (requiere admin)
    @PostMapping("/api/usuarios")
    @ResponseBody
    public ResponseEntity<?> obtenerUsuarios(@RequestBody Map<String, String> payload) {
        String adminNombre = payload.get("adminNombre");
        String adminContrasena = payload.get("adminContrasena");

        if (adminNombre == null || adminContrasena == null) {
            return ResponseEntity.badRequest().body(Map.of("mensaje", "Faltan credenciales"));
        }

        Optional<Usuario> adminOpt = usuarioServicio.autenticarAdministrador(adminNombre, adminContrasena);
        if (adminOpt.isEmpty()) {
            return ResponseEntity.status(401).body(Map.of("mensaje", "Credenciales de administrador incorrectas"));
        }

        List<Usuario> lista = usuarioServicio.obtenerTodos();
        return ResponseEntity.ok(lista);
    }

    // Páginas menú según tipo de usuario
    @GetMapping("/MenuMedico.html")
    public String menuMedico() {
        return "MenuMedico";
    }

    @GetMapping("/MenuPaciente.html")
    public String menuPaciente() {
        return "MenuPaciente";
    }

    @GetMapping("/MenuAdmin.html")
    public String menuAdmin() {
        return "MenuAdmin";
    }

    @GetMapping("/MenuInvitado.html")
    public String menuInvitado() {
        return "MenuInvitado";
    }

    // Página para registrar nuevo usuario (form)
    @GetMapping("/registrarUsuario.html")
    public String registrarUsuario() {
        return "registrarUsuario";
    }
    
    


    // Página para mostrar la lista de usuarios registrados
    @GetMapping("/usuarios.html")
    public String almacenamientoUsuarios() {
        return "usuarios"; // Debe existir almacenamientoUsuarios.html en templates
    }
}
