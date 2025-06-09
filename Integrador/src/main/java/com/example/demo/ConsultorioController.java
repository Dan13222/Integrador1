package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
@RequestMapping("/consultorios")
public class ConsultorioController {

    private static final String STORAGE_FILE = "reservas_consultorios.json";
    private final ObjectMapper objectMapper = new ObjectMapper();

    // Cargar las reservas desde un archivo JSON
    private List<ReservaConsultorio> cargarReservas() {
        File file = new File(STORAGE_FILE);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try {
            String json = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
            ReservaConsultorio[] arr = objectMapper.readValue(json, ReservaConsultorio[].class);
            return ImmutableList.copyOf(arr);
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    // Guardar las reservas en un archivo JSON
    private void guardarReservas(List<ReservaConsultorio> reservas) {
        try {
            String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(reservas);
            FileUtils.writeStringToFile(new File(STORAGE_FILE), json, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

   
    
    // Mostrar formulario de reserva
    @GetMapping("/reservarConsultorio")
    public String mostrarFormulario(Model model) {
        model.addAttribute("reservas", new ReservaConsultorio());
        model.addAttribute("consultorios", ImmutableList.of(
                new Consultorio("Consultorio A", "1", "General", "101"),
                new Consultorio("Consultorio B", "2", "Especialidad", "202")
        ));
        return "consultorio_form";  // Nombre del archivo de la vista
    }

    

    // Reservar un consultorio (POST)
    @PostMapping("/reservar")
    public String reservarConsultorio(@ModelAttribute ReservaConsultorio reserva, Model model) {
        List<ReservaConsultorio> reservasActuales = new ArrayList<>(cargarReservas());
        reservasActuales.add(reserva);
        guardarReservas(reservasActuales);
        model.addAttribute("mensaje", "Reserva de consultorio realizada con éxito");
        return "redirect:/consultorios/lista"; // Redirige a la lista de reservas
    }

    @Controller
    public class ReservadosController {

        @GetMapping("/reservados")
        public String mostrarReservados() {
            // Asumiendo que reservados.html está en src/main/resources/templates (thymeleaf)
            return "reservados"; 
        }
    }
    
    @PostMapping("/eliminar-todo")
    public String eliminarTodasLasReservas() {
        guardarReservas(new ArrayList<>()); // Guarda una lista vacía en el archivo JSON
        return "redirect:/consultorios/lista";
    }

    
    
    // Mostrar la lista de reservas de consultorios
    @GetMapping("/lista")
    public String mostrarLista(Model model) {
        List<ReservaConsultorio> reservas = cargarReservas();
        model.addAttribute("reservas", reservas);
        return "consultorio_lista"; // Asegúrate de que esta vista exista
    }

    // Exportar las reservas a Excel
    @GetMapping("/exportar")
    public ResponseEntity<InputStreamResource> exportarAExcel() throws IOException {
        List<ReservaConsultorio> reservas = cargarReservas();
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Reservas Consultorios");

        // Crear encabezados
        Row headerRow = sheet.createRow(0);
        String[] columns = {"Nombre Médico", "Apellido Médico", "Teléfono", "Correo Electrónico", "Fecha y Hora de Reserva",
                "Especialidad Médica", "Duración Consulta", "Observaciones", "Fecha de Creación", "Número Consultorio", "Sección"};
        for (int i = 0; i < columns.length; i++) {
            headerRow.createCell(i).setCellValue(columns[i]);
        }

        // Llenar datos de las reservas
        for (int i = 0; i < reservas.size(); i++) {
            ReservaConsultorio reserva = reservas.get(i);
            Row row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(reserva.getNombreMedico());
            row.createCell(1).setCellValue(reserva.getApellidoMedico());
            row.createCell(2).setCellValue(reserva.getTelefono());
            row.createCell(3).setCellValue(reserva.getCorreoElectronico());
            row.createCell(4).setCellValue(reserva.getFechaHoraReserva());
            row.createCell(5).setCellValue(reserva.getEspecialidadMedica());
            row.createCell(6).setCellValue(reserva.getDuracionConsulta());
            row.createCell(7).setCellValue(reserva.getObservaciones());
            row.createCell(8).setCellValue(reserva.getFechaCreacionReserva());
            row.createCell(9).setCellValue(reserva.getNumeroConsultorio());
            row.createCell(10).setCellValue(reserva.getSeccionConsultorio());
        }

        // Escribir archivo en un bytearray
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        workbook.write(out);
        byte[] byteArray = out.toByteArray();

        // Configurar respuesta HTTP
        InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(byteArray));
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=reservas_consultorios.xlsx");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(byteArray.length)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(resource);
    }
}

