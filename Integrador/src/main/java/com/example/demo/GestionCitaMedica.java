package com.example.demo;


public class GestionCitaMedica {
    private String nombrePaciente;
    private String apellidoPaciente;
    private String dniPaciente;
    private String telefonoPaciente;
    private String nombreMedico;
    private String apellidoMedico;
    private String especialidad;
    private String consultorio;
    private String fechaHoraCita;

    public GestionCitaMedica() {
        // Constructor vacío
    }

    // Constructor completo con los nuevos campos
    public GestionCitaMedica(String nombrePaciente, String apellidoPaciente, String dniPaciente,
                             String telefonoPaciente, String nombreMedico, String apellidoMedico,
                             String especialidad, String consultorio, String fechaHoraCita) {
        this.nombrePaciente = nombrePaciente;
        this.apellidoPaciente = apellidoPaciente;
        this.dniPaciente = dniPaciente;
        this.telefonoPaciente = telefonoPaciente;
        this.nombreMedico = nombreMedico;
        this.apellidoMedico = apellidoMedico;
        this.especialidad = especialidad;
        this.consultorio = consultorio;
        this.fechaHoraCita = fechaHoraCita;
    }

    // Getters y setters

    public String getNombrePaciente() {
        return nombrePaciente;
    }

    public void setNombrePaciente(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
    }

    public String getApellidoPaciente() {
        return apellidoPaciente;
    }

    public void setApellidoPaciente(String apellidoPaciente) {
        this.apellidoPaciente = apellidoPaciente;
    }

    public String getDniPaciente() {
        return dniPaciente;
    }

    public void setDniPaciente(String dniPaciente) {
        this.dniPaciente = dniPaciente;
    }

    public String getTelefonoPaciente() {
        return telefonoPaciente;
    }

    public void setTelefonoPaciente(String telefonoPaciente) {
        this.telefonoPaciente = telefonoPaciente;
    }

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

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getConsultorio() {
        return consultorio;
    }

    public void setConsultorio(String consultorio) {
        this.consultorio = consultorio;
    }

    public String getFechaHoraCita() {
        return fechaHoraCita;
    }

    public void setFechaHoraCita(String fechaHoraCita) {
        this.fechaHoraCita = fechaHoraCita;
    }

    @Override
    public String toString() {
        return "GestionCitaMedica{" +
                "nombrePaciente='" + nombrePaciente + '\'' +
                ", apellidoPaciente='" + apellidoPaciente + '\'' +
                ", dniPaciente='" + dniPaciente + '\'' +
                ", telefonoPaciente='" + telefonoPaciente + '\'' +
                ", nombreMedico='" + nombreMedico + '\'' +
                ", apellidoMedico='" + apellidoMedico + '\'' +
                ", especialidad='" + especialidad + '\'' +
                ", consultorio='" + consultorio + '\'' +
                ", fechaHoraCita='" + fechaHoraCita + '\'' +
                '}';
    }
}
