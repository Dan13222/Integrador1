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
public class ControllerReporteErrorClinica {

    @Autowired
    private RepositoryReporteErrorClinica repoReporte;

    @GetMapping("/reporte/nuevo")
    public String nuevoReporte(Model modelo) {
        System.out.println("nuevoReporte");
        return "/reporteNuevo";
    }

    
    @GetMapping("/reporte/todos")
    public String reportes(Model modelo) {
        System.out.println("reportes todos");
        List<ReporteErrorClinica> lst = repoReporte.findAll();
        modelo.addAttribute("listaReportes", lst);
        return "/reporteTodos";
    }

    
    @PostMapping("/reporte/insertar")
    public String insertarReporte(@ModelAttribute ReporteErrorClinica reporte) {
        System.out.println("insertarReporte");
        reporte = repoReporte.save(reporte);
        System.out.println("Se registro reporte " + reporte.getId());
        return "redirect:/menumedico";
    }

   
    @PostMapping("/reporte/eliminar")
    public String eliminarReporte(@RequestParam(name = "id") int idReporte) {
        System.out.println("eliminarReporte");
        repoReporte.deleteById(idReporte);
        System.out.println("Se elimino reporte " + idReporte);
        return "redirect:/reporte/todos";
    }

   
    @GetMapping("/reporte/edicion")
    public String reporteEdicion(@RequestParam(name = "id") int idReporte, Model modelo) {
        System.out.println("reporteEdicion");
        ReporteErrorClinica reporte = repoReporte.findById(idReporte).get();
        modelo.addAttribute("reporte", reporte);
        return "/reporteEdicion";
    }

    // Actualizar reporte
    @PostMapping("/reporte/actualizar")
    public String actualizarReporte(@ModelAttribute ReporteErrorClinica reporte) {
        System.out.println("actualizarReporte");
        ReporteErrorClinica reporteOriginal = repoReporte.findById(reporte.getId()).get();
        reporteOriginal.setNombreMedico(reporte.getNombreMedico());
        reporteOriginal.setCorreoElectronico(reporte.getCorreoElectronico());
        reporteOriginal.setFallaClinica(reporte.getFallaClinica());
        reporteOriginal.setErrorSistema(reporte.getErrorSistema());
        reporteOriginal.setFaltaImplementacionMedica(reporte.getFaltaImplementacionMedica());
        repoReporte.save(reporteOriginal);
        System.out.println("Se actualizo reporte " + reporte.getId());
        return "redirect:/reporte/todos";
    }

    @GetMapping("/reporte/buscar")
    public String buscarReporte(@RequestParam(name = "nombreMedico") String nombreMedico, Model modelo) {
        System.out.println("buscar reporte: " + nombreMedico);
        List<ReporteErrorClinica> lst = repoReporte.findAll(); 
        modelo.addAttribute("listaReportes", lst);
        return "/reporteTodos";
    }
}
