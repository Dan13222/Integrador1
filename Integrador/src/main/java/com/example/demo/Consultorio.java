package com.example.demo;

import java.io.Serializable;

public class Consultorio implements Serializable {
    private String nombreConsultorio;
    private String piso;
    private String seccion;
    private String numeroConsultorio;

    // Constructor vacío
    public Consultorio() {
    }

    // Constructor con todos los campos
    public Consultorio(String nombreConsultorio, String piso, String seccion, String numeroConsultorio) {
        this.nombreConsultorio = nombreConsultorio;
        this.piso = piso;
        this.seccion = seccion;
        this.numeroConsultorio = numeroConsultorio;
    }

    // Getters y Setters
    public String getNombreConsultorio() {
        return nombreConsultorio;
    }

    public void setNombreConsultorio(String nombreConsultorio) {
        this.nombreConsultorio = nombreConsultorio;
    }

    public String getPiso() {
        return piso;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public String getNumeroConsultorio() {
        return numeroConsultorio;
    }

    public void setNumeroConsultorio(String numeroConsultorio) {
        this.numeroConsultorio = numeroConsultorio;
    }

    @Override
    public String toString() {
        return "Consultorio{" +
                "nombreConsultorio='" + nombreConsultorio + '\'' +
                ", piso='" + piso + '\'' +
                ", seccion='" + seccion + '\'' +
                ", numeroConsultorio='" + numeroConsultorio + '\'' +
                '}';
    }
}
