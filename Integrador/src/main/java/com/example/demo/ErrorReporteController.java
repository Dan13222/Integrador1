package com.example.demo;


import com.example.demo.ErrorReporte;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;  // DÓNDE: Guava
import org.apache.commons.io.FileUtils;           // DÓNDE: Apache Commons IO
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook; // DÓNDE: Apache POI
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;                    // DÓNDE: Logback
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
@RequestMapping("/errores")
public class ErrorReporteController {

    private static final Logger logger = LoggerFactory.getLogger(ErrorReporteController.class);
    private static final String STORAGE_FILE = "reportes_errores.json";

    private final ObjectMapper objectMapper = new ObjectMapper();

    private List<ErrorReporte> cargarReportes() {
        File file = new File(STORAGE_FILE);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try {
            // Leer JSON y mapear a lista (Apache Commons FileUtils para lectura)
            String json = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
            ErrorReporte[] arr = objectMapper.readValue(json, ErrorReporte[].class);
            // Guava ImmutableList para retornar una lista inmutable
            return ImmutableList.copyOf(arr);
        } catch (IOException e) {
            logger.error("Error al cargar reportes desde JSON", e);
            return new ArrayList<>();
        }
    }

    private void guardarReportes(List<ErrorReporte> reportes) {
        try {
            String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(reportes);
            FileUtils.writeStringToFile(new File(STORAGE_FILE), json, StandardCharsets.UTF_8);
            logger.info("Reportes guardados correctamente");
        } catch (IOException e) {
            logger.error("Error al guardar reportes en JSON", e);
        }
    }

    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("errorReporte", new ErrorReporte());
        model.addAttribute("tiposErrores", ImmutableList.of(
            "Error de sistema", "Error de interfaz", "Error de datos", "Otro"
        )); // Guava para crear lista inmutable
        return "formulario_error";
    }

    @PostMapping("/enviar")
    public String enviarReporte(@ModelAttribute ErrorReporte errorReporte, Model model) {
        logger.info("Nuevo reporte recibido de {}", errorReporte.getNombrePaciente());

        List<ErrorReporte> actuales = new ArrayList<>(cargarReportes());
        actuales.add(errorReporte);
        guardarReportes(actuales);

        model.addAttribute("mensaje", "Reporte enviado correctamente");
        return "redirect:/MenuPaciente.html";

    }

    @GetMapping("/lista")
    public String mostrarReportes(Model model) {
        List<ErrorReporte> reportes = cargarReportes();
        model.addAttribute("reportes", reportes);
        return "lista_reportes";
    }

    @GetMapping("/exportar")
    public ResponseEntity<InputStreamResource> exportarExcel() throws IOException {
        List<ErrorReporte> reportes = cargarReportes();

        Workbook workbook = new XSSFWorkbook(); // DÓNDE: Apache POI
        Sheet sheet = workbook.createSheet("Reportes");

        // Crear encabezados
        Row header = sheet.createRow(0);
        String[] columnas = {"Nombre Paciente", "Correo Electrónico", "Tipo de Error", "Descripción"};
        for (int i = 0; i < columnas.length; i++) {
            Cell cell = header.createCell(i);
            cell.setCellValue(columnas[i]);
        }

        // Llenar filas
        int rowNum = 1;
        for (ErrorReporte rep : reportes) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(rep.getNombrePaciente());
            row.createCell(1).setCellValue(rep.getCorreoElectronico());
            row.createCell(2).setCellValue(rep.getTipoError());
            row.createCell(3).setCellValue(rep.getDescripcionProblema());
        }

        // Autoajustar columnas
        for (int i = 0; i < columnas.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Escribir a ByteArrayOutputStream para enviar como respuesta
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        workbook.write(out);
        workbook.close();

        InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(out.toByteArray()));

        logger.info("Exportando reportes a Excel");

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=reportes_errores.xlsx")
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(resource);
    }
}
