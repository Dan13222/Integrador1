package com.example.demo;

import java.io.Serializable;

public class ReservaConsultorio implements Serializable {
    private String nombreMedico;
    private String apellidoMedico;
    private String telefono;
    private String correoElectronico;
    private String fechaHoraReserva;
    private String especialidadMedica;
    private int duracionConsulta;
    private String observaciones;
    private String fechaCreacionReserva;
    private String numeroConsultorio;
    private String seccionConsultorio;

    // Constructor vacío
    public ReservaConsultorio() {
    }

    // Constructor con todos los campos
    public ReservaConsultorio(String nombreMedico, String apellidoMedico, String telefono, String correoElectronico,
                              String fechaHoraReserva, String especialidadMedica, int duracionConsulta, String observaciones,
                              String fechaCreacionReserva, String numeroConsultorio, String seccionConsultorio) {
        this.nombreMedico = nombreMedico;
        this.apellidoMedico = apellidoMedico;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.fechaHoraReserva = fechaHoraReserva;
        this.especialidadMedica = especialidadMedica;
        this.duracionConsulta = duracionConsulta;
        this.observaciones = observaciones;
        this.fechaCreacionReserva = fechaCreacionReserva;
        this.numeroConsultorio = numeroConsultorio;
        this.seccionConsultorio = seccionConsultorio;
    }

    // Getters y Setters
    public String getNombreMedico() {
        return nombreMedico;
    }

    public void setNombreMedico(String nombreMedico) {
        this.nombreMedico = nombreMedico;
    }

    public String getApellidoMedico() {
        return apellidoMedico;
    }

    public void setApellidoMedico(String apellidoMedico) {
        this.apellidoMedico = apellidoMedico;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getFechaHoraReserva() {
        return fechaHoraReserva;
    }

    public void setFechaHoraReserva(String fechaHoraReserva) {
        this.fechaHoraReserva = fechaHoraReserva;
    }

    public String getEspecialidadMedica() {
        return especialidadMedica;
    }

    public void setEspecialidadMedica(String especialidadMedica) {
        this.especialidadMedica = especialidadMedica;
    }

    public int getDuracionConsulta() {
        return duracionConsulta;
    }

    public void setDuracionConsulta(int duracionConsulta) {
        this.duracionConsulta = duracionConsulta;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getFechaCreacionReserva() {
        return fechaCreacionReserva;
    }

    public void setFechaCreacionReserva(String fechaCreacionReserva) {
        this.fechaCreacionReserva = fechaCreacionReserva;
    }

    public String getNumeroConsultorio() {
        return numeroConsultorio;
    }

    public void setNumeroConsultorio(String numeroConsultorio) {
        this.numeroConsultorio = numeroConsultorio;
    }

    public String getSeccionConsultorio() {
        return seccionConsultorio;
    }

    public void setSeccionConsultorio(String seccionConsultorio) {
        this.seccionConsultorio = seccionConsultorio;
    }

    @Override
    public String toString() {
        return "ReservaConsultorio{" +
                "nombreMedico='" + nombreMedico + '\'' +
                ", apellidoMedico='" + apellidoMedico + '\'' +
                ", telefono='" + telefono + '\'' +
                ", correoElectronico='" + correoElectronico + '\'' +
                ", fechaHoraReserva='" + fechaHoraReserva + '\'' +
                ", especialidadMedica='" + especialidadMedica + '\'' +
                ", duracionConsulta=" + duracionConsulta +
                ", observaciones='" + observaciones + '\'' +
                ", fechaCreacionReserva='" + fechaCreacionReserva + '\'' +
                ", numeroConsultorio='" + numeroConsultorio + '\'' +
                ", seccionConsultorio='" + seccionConsultorio + '\'' +
                '}';
    }
}
