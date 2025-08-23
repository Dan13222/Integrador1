package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller

public class ControllerSistem {

	        
	        @GetMapping("/menuadmin")
	        public String mostrarMenuAdmin() {
	            return "menuadmin"; // Esto buscará el archivo 'menuadmin.html'
	        }

	    }

	    