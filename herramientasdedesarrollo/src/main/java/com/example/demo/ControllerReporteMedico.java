package com.example.clinica;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ControllerReporteMedico {

    @Autowired
    private RepositoryReporteMedico repoReportes;

    @Autowired
    private PdfService pdfService;

    @GetMapping("/reportes/nuevo")
    public String nuevoReporte(Model modelo) {
        System.out.println("nuevoReporte");
        modelo.addAttribute("reporte", new ReporteMedico());
        return "/reportesNuevo";
    }

    @GetMapping("/reportes/todos")
    public String reportes(Model modelo) {
        System.out.println("reportes todos");
        List<ReporteMedico> lst = repoReportes.findAll();
        modelo.addAttribute("listaReportes", lst);
        return "/reportesTodos";
    }

    @PostMapping("/reportes/insertar")
    public String insertarReporte(@ModelAttribute ReporteMedico reporte) {
        System.out.println("insertarReporte");
        reporte = repoReportes.save(reporte);
        System.out.println("Se registró reporte " + reporte.getId());
        return "redirect:/reportes/todos";
    }

    @PostMapping("/reportes/eliminar")
    public String eliminarReporte(@RequestParam(name = "id") int idReporte) {
        System.out.println("eliminarReporte");
        repoReportes.deleteById(idReporte);
        System.out.println("Se eliminó reporte " + idReporte);
        return "redirect:/reportes/todos";
    }

    @GetMapping("/reportes/edicion")
    public String reportesEdicion(@RequestParam(name = "id") int idReporte, Model modelo) {
        System.out.println("reportesEdicion");
        ReporteMedico reporte = repoReportes.findById(idReporte).get();
        modelo.addAttribute("reporte", reporte);
        return "/reportesEdicion";
    }

    @PostMapping("/reportes/actualizar")
    public String actualizarReporte(@ModelAttribute ReporteMedico reporte) {
        System.out.println("actualizarReporte");
        ReporteMedico original = repoReportes.findById(reporte.getId()).get();

        original.setInstitucion(reporte.getInstitucion());
        original.setDatosPaciente(reporte.getDatosPaciente());
        original.setFechaLugar(reporte.getFechaLugar());
        original.setMotivoConsulta(reporte.getMotivoConsulta());
        original.setAntecedentes(reporte.getAntecedentes());
        original.setExamenFisico(reporte.getExamenFisico());
        original.setExamenesAuxiliares(reporte.getExamenesAuxiliares());
        original.setDiagnostico(reporte.getDiagnostico());
        original.setTratamiento(reporte.getTratamiento());
        original.setEvolucion(reporte.getEvolucion());
        original.setRecomendaciones(reporte.getRecomendaciones());
        original.setConclusion(reporte.getConclusion());
        original.setFirmaMedico(reporte.getFirmaMedico());

        repoReportes.save(original);
        System.out.println("Se actualizó reporte " + reporte.getId());
        return "redirect:/reportes/todos";
    }

    @GetMapping("/reportes/buscar")
    public String buscarReportes(@RequestParam(name = "cadena") String cadena, Model modelo) {
        System.out.println("buscar reportes: " + cadena);
        List<ReporteMedico> lst = repoReportes.findByDatosPacienteContainingIgnoreCase(cadena);
        modelo.addAttribute("listaReportes", lst);
        return "/reportesTodos";
    }

    // ====== Exportar a PDF ======
    @GetMapping("/reportes/pdf")
    public ResponseEntity<byte[]> pdf(@RequestParam(name = "id") int id) {
        Optional<ReporteMedico> opt = repoReportes.findById(id);
        if (opt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        byte[] pdf = pdfService.generarReporteMedicoPdf(opt.get());
        String nombre = "reporte_medico_" + id + ".pdf";

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=" + nombre)
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }
}
