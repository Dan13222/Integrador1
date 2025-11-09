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
public class ControllerTramite {

    @Autowired
    private RepositoryTramite repoTramite;

    @GetMapping("/tramites/nuevo")
    public String nuevoTramite(Model modelo) {
        System.out.println("nuevoTramite");
        return "/tramiteNuevo";
    }

    @GetMapping("/tramites/todos")
    public String tramites(Model modelo) {
        System.out.println("tramites todos");
        List<Tramite> lst = repoTramite.findAll();
        modelo.addAttribute("listaTramites", lst);
        return "/tramiteTodos";
    }

    @PostMapping("/tramites/insertar")
    public String insertarTramite(@ModelAttribute Tramite tramite) {
        System.out.println("insertarTramite");
        tramite = repoTramite.save(tramite);
        System.out.println("Se registro tramite " + tramite.getId());
        return "redirect:/tramites/todos";
    }

    @PostMapping("/tramites/eliminar")
    public String eliminarTramite(@RequestParam(name = "id") int idTramite) {
        System.out.println("eliminarTramite");
        repoTramite.deleteById(idTramite);
        System.out.println("Se elimino tramite " + idTramite);
        return "redirect:/tramites/todos";
    }

    @GetMapping("/tramites/edicion")
    public String tramitesEdicion(@RequestParam(name = "id") int idTramite, Model modelo) {
        System.out.println("tramitesEdicion");
        Tramite tramite = repoTramite.findById(idTramite).get();
        modelo.addAttribute("tramite", tramite);
        return "/tramiteEdicion";
    }

    @PostMapping("/tramites/actualizar")
    public String actualizarTramite(@ModelAttribute Tramite tramite) {
        System.out.println("actualizarTramite");
        Tramite tramiteOriginal = repoTramite.findById(tramite.getId()).get();

        tramiteOriginal.setNombreAtendido(tramite.getNombreAtendido());
        tramiteOriginal.setApellidoAtendido(tramite.getApellidoAtendido());
        tramiteOriginal.setDniAtendido(tramite.getDniAtendido());
        tramiteOriginal.setTelefonoAtendido(tramite.getTelefonoAtendido());

        tramiteOriginal.setNombreDoctor(tramite.getNombreDoctor());
        tramiteOriginal.setApellidoDoctor(tramite.getApellidoDoctor());
        tramiteOriginal.setEspecialidadDoctor(tramite.getEspecialidadDoctor());
        tramiteOriginal.setConsultorioDoctor(tramite.getConsultorioDoctor());

        tramiteOriginal.setFechaHora(tramite.getFechaHora());

        repoTramite.save(tramiteOriginal);
        System.out.println("Se actualizo tramite " + tramite.getId());
        return "redirect:/tramites/todos";
    }

    @GetMapping("/tramites/buscar")
    public String buscarTramites(@RequestParam(name = "cadena") String cadena, Model modelo) {
        System.out.println("buscar tramites: " + cadena);
        List<Tramite> lst = repoTramite.findByNombreAtendidoContainingIgnoreCase(cadena);
        modelo.addAttribute("listaTramites", lst);
        return "/tramiteTodos";
    }
}
