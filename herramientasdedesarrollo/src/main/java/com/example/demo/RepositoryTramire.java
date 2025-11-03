package com.example.clinica;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryTramite extends JpaRepository<Tramite, Integer> {

    // Ejemplo para buscar por nombre atendido (similar a búsqueda de libros)
    public List<Tramite> findByNombreAtendidoContainingIgnoreCase(String nombre);
}

