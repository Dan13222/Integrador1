package com.example.demo;


import java.io.Serializable;

public class ErrorReporte implements Serializable {
    private String nombrePaciente;
    private String correoElectronico;
    private String tipoError;
    private String descripcionProblema;

    // Constructor vacío
    public ErrorReporte() {
    }

    // Constructor con todos los campos
    public ErrorReporte(String nombrePaciente, String correoElectronico, String tipoError, String descripcionProblema) {
        this.nombrePaciente = nombrePaciente;
        this.correoElectronico = correoElectronico;
        this.tipoError = tipoError;
        this.descripcionProblema = descripcionProblema;
    }

    // Getters y Setters
    public String getNombrePaciente() {
        return nombrePaciente;
    }

    public void setNombrePaciente(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getTipoError() {
        return tipoError;
    }

    public void setTipoError(String tipoError) {
        this.tipoError = tipoError;
    }

    public String getDescripcionProblema() {
        return descripcionProblema;
    }

    public void setDescripcionProblema(String descripcionProblema) {
        this.descripcionProblema = descripcionProblema;
    }

    // Override toString (opcional pero útil)
    @Override
    public String toString() {
        return "ErrorReporte{" +
                "nombrePaciente='" + nombrePaciente + '\'' +
                ", correoElectronico='" + correoElectronico + '\'' +
                ", tipoError='" + tipoError + '\'' +
                ", descripcionProblema='" + descripcionProblema + '\'' +
                '}';
    }
}
