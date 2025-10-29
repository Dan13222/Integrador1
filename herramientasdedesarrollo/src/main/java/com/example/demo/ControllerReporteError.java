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
public class ControllerReporteError {

    @Autowired
    private RepositoryReporteError repoReporteError;

    @GetMapping("/errores/nuevo")
    public String nuevoReporte(Model modelo) {
        System.out.println("nuevoReporte");
        return "/errorNuevo";
    }

    @GetMapping("/errores/todos")
    public String listarReportes(Model modelo) {
        System.out.println("listarReportes");
        List<ReporteError> lst = repoReporteError.findAll();
        modelo.addAttribute("listaReportes", lst);
        return "/errorTodos";
    }

    @PostMapping("/errores/insertar")
    public String insertarReporte(@ModelAttribute ReporteError reporte) {
        System.out.println("insertarReporte");
        reporte = repoReporteError.save(reporte);
        System.out.println("Se registro reporte " + reporte.getId());
        return "redirect:/MenuPaciente";
        
    }

    @PostMapping("/errores/eliminar")
    public String eliminarReporte(@RequestParam(name = "id") int idReporte) {
        System.out.println("eliminarReporte");
        repoReporteError.deleteById(idReporte);
        System.out.println("Se elimino reporte " + idReporte);
        return "redirect:/errores/todos";
    }

    @GetMapping("/errores/edicion")
    public String reporteEdicion(@RequestParam(name = "id") int idReporte, Model modelo) {
        System.out.println("reporteEdicion");
        ReporteError reporte = repoReporteError.findById(idReporte).get();
        modelo.addAttribute("reporte", reporte);
        return "/errorEdicion";
    }

    @PostMapping("/errores/actualizar")
    public String actualizarReporte(@ModelAttribute ReporteError reporte) {
        System.out.println("actualizarReporte");
        ReporteError reporteOriginal = repoReporteError.findById(reporte.getId()).get();
        reporteOriginal.setNombrePaciente(reporte.getNombrePaciente());
        reporteOriginal.setCorreoElectronico(reporte.getCorreoElectronico());
        reporteOriginal.setErrorSistema(reporte.getErrorSistema());
        reporteOriginal.setInfoAdicional(reporte.getInfoAdicional());
        repoReporteError.save(reporteOriginal);
        System.out.println("Se actualizo reporte " + reporte.getId());
        return "redirect:/errores/todos";
    }

    @GetMapping("/errores/buscar")
    public String buscarReportes(@RequestParam(name = "cadena") String cadena, Model modelo) {
        System.out.println("buscar reportes: " + cadena);
        List<ReporteError> lst = repoReporteError.findByErrorSistemaContainingIgnoreCase(cadena);
        modelo.addAttribute("listaReportes", lst);
        return "/errorTodos";
    }
}
