package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList; // Guava
import org.apache.commons.io.FileUtils;         // Apache Commons IO
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook; // Apache POI
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;                // Logback
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/gestionCitas")
public class GestionCitaMedicaController {

    private static final Logger logger = LoggerFactory.getLogger(GestionCitaMedicaController.class);
    private static final String STORAGE_FILE = "gestion_citas_medicas.json";

    private final ObjectMapper objectMapper = new ObjectMapper();

    private List<GestionCitaMedica> cargarCitas() {
        File file = new File(STORAGE_FILE);
        if (!file.exists()) return new ArrayList<>();
        try {
            String json = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
            GestionCitaMedica[] arr = objectMapper.readValue(json, GestionCitaMedica[].class);
            return ImmutableList.copyOf(arr);
        } catch (IOException e) {
            logger.error("Error al cargar citas", e);
            return new ArrayList<>();
        }
    }

    private void guardarCitas(List<GestionCitaMedica> citas) {
        try {
            String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(citas);
            FileUtils.writeStringToFile(new File(STORAGE_FILE), json, StandardCharsets.UTF_8);
            logger.info("Citas guardadas correctamente");
        } catch (IOException e) {
            logger.error("Error al guardar citas", e);
        }
    }

    @GetMapping("/nueva")
    public String mostrarFormulario(Model model) {
        model.addAttribute("gestionCitaMedica", new GestionCitaMedica());
        return "formulario_gestion_cita";
    }

    @PostMapping("/enviar")
    public String enviarFormulario(@ModelAttribute GestionCitaMedica gestionCitaMedica, Model model) {
        logger.info("Nueva cita médica registrada para paciente {}", gestionCitaMedica.getNombrePaciente());
        List<GestionCitaMedica> citas = new ArrayList<>(cargarCitas());
        citas.add(gestionCitaMedica);
        guardarCitas(citas);
        model.addAttribute("mensaje", "Cita enviada correctamente");
        // Cambiado redireccionamiento para que no vaya a MenuPaciente.html
        return "redirect:/MenuAdmin.html";
    }

   
    @GetMapping("/lista")
    public String verCitas(Model model) {
        model.addAttribute("citas", cargarCitas()); // <- este nombre sí es el que usa tu HTML
        return "lista_gestion_citas";
    }


    @GetMapping("/exportar")
    public ResponseEntity<InputStreamResource> exportarCitas() throws IOException {
        List<GestionCitaMedica> citas = cargarCitas();

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Citas Médicas");

        String[] columnas = {
                "Nombre Paciente", "Apellido Paciente", "DNI Paciente", "Teléfono Paciente",
                "Nombre Médico", "Apellido Médico", "Especialidad", "Consultorio", "Fecha y Hora Cita"
        };

        Row header = sheet.createRow(0);
        for (int i = 0; i < columnas.length; i++) {
            header.createCell(i).setCellValue(columnas[i]);
        }

        int rowNum = 1;
        for (GestionCitaMedica cita : citas) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(cita.getNombrePaciente());
            row.createCell(1).setCellValue(cita.getApellidoPaciente());
            row.createCell(2).setCellValue(cita.getDniPaciente());
            row.createCell(3).setCellValue(cita.getTelefonoPaciente());
            row.createCell(4).setCellValue(cita.getNombreMedico());
            row.createCell(5).setCellValue(cita.getApellidoMedico());
            row.createCell(6).setCellValue(cita.getEspecialidad());
            row.createCell(7).setCellValue(cita.getConsultorio());
            row.createCell(8).setCellValue(cita.getFechaHoraCita());
        }

        for (int i = 0; i < columnas.length; i++) {
            sheet.autoSizeColumn(i);
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        workbook.write(out);
        workbook.close();

        InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(out.toByteArray()));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=gestion_citas_medicas.xlsx")
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(resource);
    }
}
