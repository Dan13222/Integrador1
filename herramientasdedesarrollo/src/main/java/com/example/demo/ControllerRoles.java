package com.example.clinica;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ControllerRoles {

    @Autowired
    private RolesRepository repoRoles;

    // Mostrar formulario para registrar un nuevo rol
    @GetMapping("/roles/nuevo")
    public String nuevoRol(Model model) {
        model.addAttribute("rol", new Roles());
        return "rolesNuevo";
    }

    // Insertar un nuevo rol
    @PostMapping("/roles/insertar")
    public String insertarRol(@ModelAttribute Roles rol) {
        repoRoles.save(rol);
        return "redirect:/roles/todos";
    }

    // Listar todos los roles
    @GetMapping("/roles/todos")
    public String listarRoles(Model model) {
        List<Roles> lista = repoRoles.findAll();
        model.addAttribute("listaRoles", lista);
        return "rolesTodos";
    }

    // Mostrar formulario para editar un rol existente
    @GetMapping("/roles/edicion")
    public String editarRol(@RequestParam("id") int id, Model model) {
        Roles rol = repoRoles.findById(id).orElse(null);
        if (rol == null) {
            return "redirect:/roles/todos";
        }
        model.addAttribute("rol", rol);
        return "rolesEdicion";
    }

    // Actualizar un rol existente
    @PostMapping("/roles/actualizar")
    public String actualizarRol(@ModelAttribute Roles rol) {
        System.out.println("ACTUALIZAR: " + rol.getId() + " - " + rol.getNombre());
        repoRoles.save(rol);
        return "redirect:/roles/todos";
    }

    // Eliminar un rol por ID
    @PostMapping("/roles/eliminar")
    public String eliminarRol(@RequestParam("id") int id) {
        if (repoRoles.existsById(id)) {
            repoRoles.deleteById(id);
        }
        return "redirect:/roles/todos";
    }
}
