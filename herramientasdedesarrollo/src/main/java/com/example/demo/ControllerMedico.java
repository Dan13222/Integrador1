package com.example.clinica;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ControllerMedico {

    @Autowired
    private RepositoryMedico repoMedico;
    
    @GetMapping("/medico/nuevo")
    public String nuevoMedico(Model modelo) {
        System.out.println("nuevoMedico");
        return "/medicoNuevo";
    }
    
    @GetMapping("/medico/todos")
    public String medicos(Model modelo) {
        System.out.println("medicos todos");
        List<Medico> lst = repoMedico.findAll();
        modelo.addAttribute("listaMedicos", lst);
        return "/medicosTodos";
    }
    
    @PostMapping("/medico/insertar")
    public String insertarMedico(@ModelAttribute Medico medico) {
        System.out.println("insertarMedico");
        medico = repoMedico.save(medico);
        System.out.println("Se registro medico " + medico.getId());
        return "redirect:/menumedico";
    }
    
    @PostMapping("/medico/eliminar")
    public String eliminarMedico(@RequestParam(name = "id") int idMedico) {
        System.out.println("eliminarMedico");
        repoMedico.deleteById(idMedico);
        System.out.println("Se elimino medico " + idMedico);
        return "redirect:/medico/todos";
    }
    
    @GetMapping("/medico/edicion")
    public String medicosEdicion(@RequestParam(name = "id") int idMedico, Model modelo) {
        System.out.println("medicosEdicion");
        Medico medico = repoMedico.findById(idMedico).get();
        modelo.addAttribute("medico", medico);
        return "/medicoEdicion";
    }
    
    @PostMapping("/medico/actualizar")
    public String actualizarMedico(@ModelAttribute Medico medico) {
        System.out.println("actualizarMedico");
        Medico medicoOriginal = repoMedico.findById(medico.getId()).get();
        medicoOriginal.setNombre(medico.getNombre());
        medicoOriginal.setApellido(medico.getApellido());
        medicoOriginal.setTelefono(medico.getTelefono());
        medicoOriginal.setCorreoElectronico(medico.getCorreoElectronico());
        medicoOriginal.setEspecialidadMedica(medico.getEspecialidadMedica());
        medicoOriginal.setDni(medico.getDni());
        medicoOriginal.setNumero(medico.getNumero());
        medicoOriginal.setRequerimientosMedicos(medico.getRequerimientosMedicos());
        medicoOriginal.setFechaHoraDisponibilidad(medico.getFechaHoraDisponibilidad());
        repoMedico.save(medicoOriginal);
        System.out.println("Se actualizo medico " + medico.getId());
        return "redirect:/medico/todos";
    }
    
    @GetMapping("/medico/buscar")
    public String buscarMedicos(@RequestParam(name = "cadena") String cadena, Model modelo) {
        System.out.println("buscar medicos: " + cadena);
        List<Medico> lst = repoMedico.findByNombreContainingIgnoreCase(cadena);
        modelo.addAttribute("listaMedicos", lst);
        return "/medicosTodos";
    }
}
