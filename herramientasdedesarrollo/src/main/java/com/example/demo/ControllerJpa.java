package com.example.clinica;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ControllerJpa {

	@Autowired
	private EstudiantesRepository estudiantesRepo;
	
	@GetMapping("/estudiantes/nuevo")
	public String nuevoEstudiante() {
		return "estudianteNuevo.html";
	}
	
	@PostMapping("/estudiantes/insertar")
	public String insertarEstudiante(@ModelAttribute Estudiantes nuevo) {
		System.out.println("insertarEstudiante");
		Estudiantes e = estudiantesRepo.save(nuevo);
		System.out.println("ID generado: " + e.getId());
		return "estudianteNuevo.html";
	}
	
	@GetMapping("/actualizar")
	public String actualizarEstudiante() {
		System.out.println("actualizarEstudiante");
		Estudiantes e = estudiantesRepo.findById(1).get();
		e.setNombre("Paul UTP");
		e.setEmail("paul@utp.edu.pe");
		e = estudiantesRepo.save(e);
		return "index";
	}
}
