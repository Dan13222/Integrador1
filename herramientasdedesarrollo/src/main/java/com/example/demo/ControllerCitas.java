package com.example.clinica;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ControllerCitas {

    @Autowired
    private RepositoryCitas repoCitas;

    @GetMapping("/citas/nueva")
    public String nuevaCita(Model modelo) {
        System.out.println("nuevaCita");
        return "/citaNueva";
    }

    @GetMapping("/citas/todas")
    public String citas(Model modelo) {
        System.out.println("citas todas");
        List<CitaMedica> lst = repoCitas.findAll();
        modelo.addAttribute("listaCitas", lst);
        return "/citasTodas";
    }

    @PostMapping("/citas/insertar")
    public String insertarCita(@ModelAttribute CitaMedica cita) {
        System.out.println("insertarCita");
        cita = repoCitas.save(cita);
        System.out.println("Se registró cita ID: " + cita.getId());
        return "redirect:/MenuPaciente";

    }
    
    
    @GetMapping("/MenuPaciente")
    public String mostrarMenuPaciente() {
        return "MenuPaciente"; // Esto carga MenuPaciente.html desde /templates
    }



    


    @GetMapping("/menumedico")
    public String mostrarmenuMedico() {
        return "menumedico"; // REDIRECICION MEDICO PARA REDIRECT:// 
    }

    
    @GetMapping("/API")
    public String mostrarAPI() {
    	return "API";
    }
    
    
    @GetMapping("/laboratorio")
    public String mostrarlaboratorio() {
    	return "laboratorio";
    }
    
    
    @GetMapping("/historial")
    public String mostrarhistorial() {
    	return "historial";
    }
    
   
    
    @PostMapping("/citas/eliminar")
    public String eliminarCita(@RequestParam(name = "id") int idCita) {
        System.out.println("eliminarCita");
        repoCitas.deleteById(idCita);
        System.out.println("Se eliminó cita ID: " + idCita);
        return "redirect:/citas/todas";
    }

    @GetMapping("/citas/edicion")
    public String editarCita(@RequestParam(name = "id") int idCita, Model modelo) {
        System.out.println("editarCita");
        CitaMedica cita = repoCitas.findById(idCita).get();
        modelo.addAttribute("cita", cita);
        return "/citaEdicion";
    }

    @PostMapping("/citas/actualizar")
    public String actualizarCita(@ModelAttribute CitaMedica cita) {
        System.out.println("actualizarCita");
        CitaMedica original = repoCitas.findById(cita.getId()).get();

        original.setNombre(cita.getNombre());
        original.setApellido(cita.getApellido());
        original.setFechaInicioSintomas(cita.getFechaInicioSintomas());
        original.setDescripcionSintomas(cita.getDescripcionSintomas());
        original.setDni(cita.getDni());
        original.setDiscapacidad(cita.getDiscapacidad());
        original.setCondicionesCronicas(cita.getCondicionesCronicas());
        original.setOtraInformacion(cita.getOtraInformacion());
        original.setTelefonoPersonal(cita.getTelefonoPersonal());
        original.setCorreoElectronico(cita.getCorreoElectronico());
        original.setAntecedentes(cita.getAntecedentes());
        original.setTelefonoContacto(cita.getTelefonoContacto());

        repoCitas.save(original);
        System.out.println("Se actualizó cita ID: " + cita.getId());
        return "redirect:/citas/todas";
    }

    @GetMapping("/citas/buscar")
    public String buscarCita(@RequestParam(name = "cadena") String cadena, Model modelo) {
        System.out.println("buscar cita: " + cadena);
        List<CitaMedica> lst = repoCitas.findByNombreContainingIgnoreCase(cadena);
        modelo.addAttribute("listaCitas", lst);
        return "/citasTodas";
    }
}
