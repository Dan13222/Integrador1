package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList; //  Guava
import org.apache.commons.io.FileUtils;         //  Apache Commons IO
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook; //  Apache POI
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;                //  Logback
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
@RequestMapping("/citas")
public class CitaMedicaController {

    private static final Logger logger = LoggerFactory.getLogger(CitaMedicaController.class);
    private static final String STORAGE_FILE = "citas_medicas.json";

    private final ObjectMapper objectMapper = new ObjectMapper();

    private List<CitaMedica> cargarCitas() {
        File file = new File(STORAGE_FILE);
        if (!file.exists()) return new ArrayList<>();
        try {
            String json = FileUtils.readFileToString(file, StandardCharsets.UTF_8); // Apache Commons IO
            CitaMedica[] arr = objectMapper.readValue(json, CitaMedica[].class);
            return ImmutableList.copyOf(arr); // Guava
        } catch (IOException e) {
            logger.error("Error al cargar citas", e);
            return new ArrayList<>();
        }
    }

    private void guardarCitas(List<CitaMedica> citas) {
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
        model.addAttribute("citaMedica", new CitaMedica());
        return "formulario_cita";
    }

    @PostMapping("/enviar")
    public String enviarFormulario(@ModelAttribute CitaMedica citaMedica, Model model) {
        logger.info("Nueva cita médica registrada para {}", citaMedica.getCorreoElectronico());
        List<CitaMedica> citas = new ArrayList<>(cargarCitas());
        citas.add(citaMedica);
        guardarCitas(citas);
        model.addAttribute("mensaje", "Cita enviada correctamente");
        return "redirect:/MenuPaciente.html";
    }

    @GetMapping("/lista")
    public String verCitas(Model model) {
        model.addAttribute("citas", cargarCitas());
        return "lista_citas";
    }

    @GetMapping("/exportar")
    public ResponseEntity<InputStreamResource> exportarCitas() throws IOException {
        List<CitaMedica> citas = cargarCitas();

        Workbook workbook = new XSSFWorkbook(); // Apache POI
        Sheet sheet = workbook.createSheet("Citas Médicas");

        String[] columnas = {
            "Motivo", "Síntomas", "Fecha Inicio", "Antecedentes", "Medicamentos",
            "Consultas Previas", "Condiciones Crónicas", "Otra Info", "Teléfono",
            "Correo", "Contacto Emergencia", "Tel. Contacto"
        };

        Row header = sheet.createRow(0);
        for (int i = 0; i < columnas.length; i++) {
            header.createCell(i).setCellValue(columnas[i]);
        }

        int rowNum = 1;
        for (CitaMedica cita : citas) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(cita.getMotivoConsulta());
            row.createCell(1).setCellValue(cita.getDescripcionSintomas());
            row.createCell(2).setCellValue(cita.getFechaInicioSintomas());
            row.createCell(3).setCellValue(cita.getAntecedentesMedicos());
            row.createCell(4).setCellValue(cita.getMedicamentosActuales());
            row.createCell(5).setCellValue(cita.getConsultasPrevias());
            row.createCell(6).setCellValue(cita.getCondicionesCronicas());
            row.createCell(7).setCellValue(cita.getOtraInformacion());
            row.createCell(8).setCellValue(cita.getTelefonoPersonal());
            row.createCell(9).setCellValue(cita.getCorreoElectronico());
            row.createCell(10).setCellValue(cita.getContactoEmergencia());
            row.createCell(11).setCellValue(cita.getTelefonoContacto());
        }

        for (int i = 0; i < columnas.length; i++) {
            sheet.autoSizeColumn(i);
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        workbook.write(out);
        workbook.close();

        InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(out.toByteArray()));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=citas_medicas.xlsx")
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(resource);
    }
}
