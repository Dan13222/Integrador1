package com.example.clinica;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Roles {

    @Id
    @GeneratedValue(generator = "sec_roles")
    @SequenceGenerator(name = "sec_roles", sequenceName = "sec_roles", allocationSize = 1)
    private int id;
    
    private String nombre;

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
 
