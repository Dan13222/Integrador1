package com.example.clinica;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Usuarios {

    @Id
    @GeneratedValue(generator = "sec_usuarios")
    @SequenceGenerator(name = "sec_usuarios", sequenceName = "sec_usuarios", allocationSize = 1)
    private int id;

    private String nombre;

    private String password;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
