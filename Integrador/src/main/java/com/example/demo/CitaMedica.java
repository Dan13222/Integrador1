package com.example.demo;

import java.io.Serializable;

public class CitaMedica implements Serializable {
    private String motivoConsulta;
    private String descripcionSintomas;
    private String fechaInicioSintomas;
    private String antecedentesMedicos;
    private String medicamentosActuales;
    private String consultasPrevias;
    private String condicionesCronicas;
    private String otraInformacion;
    private String telefonoPersonal;
    private String correoElectronico;
    private String contactoEmergencia;
    private String telefonoContacto;

    // Constructor vacío
    public CitaMedica() {}

    // Constructor completo
    public CitaMedica(String motivoConsulta, String descripcionSintomas, String fechaInicioSintomas,
                      String antecedentesMedicos, String medicamentosActuales, String consultasPrevias,
                      String condicionesCronicas, String otraInformacion, String telefonoPersonal,
                      String correoElectronico, String contactoEmergencia, String telefonoContacto) {
        this.motivoConsulta = motivoConsulta;
        this.descripcionSintomas = descripcionSintomas;
        this.fechaInicioSintomas = fechaInicioSintomas;
        this.antecedentesMedicos = antecedentesMedicos;
        this.medicamentosActuales = medicamentosActuales;
        this.consultasPrevias = consultasPrevias;
        this.condicionesCronicas = condicionesCronicas;
        this.otraInformacion = otraInformacion;
        this.telefonoPersonal = telefonoPersonal;
        this.correoElectronico = correoElectronico;
        this.contactoEmergencia = contactoEmergencia;
        this.telefonoContacto = telefonoContacto;
    }

    // Getters y Setters
    public String getMotivoConsulta() {
        return motivoConsulta;
    }

    public void setMotivoConsulta(String motivoConsulta) {
        this.motivoConsulta = motivoConsulta;
    }

    public String getDescripcionSintomas() {
        return descripcionSintomas;
    }

    public void setDescripcionSintomas(String descripcionSintomas) {
        this.descripcionSintomas = descripcionSintomas;
    }

    public String getFechaInicioSintomas() {
        return fechaInicioSintomas;
    }

    public void setFechaInicioSintomas(String fechaInicioSintomas) {
        this.fechaInicioSintomas = fechaInicioSintomas;
    }

    public String getAntecedentesMedicos() {
        return antecedentesMedicos;
    }

    public void setAntecedentesMedicos(String antecedentesMedicos) {
        this.antecedentesMedicos = antecedentesMedicos;
    }

    public String getMedicamentosActuales() {
        return medicamentosActuales;
    }

    public void setMedicamentosActuales(String medicamentosActuales) {
        this.medicamentosActuales = medicamentosActuales;
    }

    public String getConsultasPrevias() {
        return consultasPrevias;
    }

    public void setConsultasPrevias(String consultasPrevias) {
        this.consultasPrevias = consultasPrevias;
    }

    public String getCondicionesCronicas() {
        return condicionesCronicas;
    }

    public void setCondicionesCronicas(String condicionesCronicas) {
        this.condicionesCronicas = condicionesCronicas;
    }

    public String getOtraInformacion() {
        return otraInformacion;
    }

    public void setOtraInformacion(String otraInformacion) {
        this.otraInformacion = otraInformacion;
    }

    public String getTelefonoPersonal() {
        return telefonoPersonal;
    }

    public void setTelefonoPersonal(String telefonoPersonal) {
        this.telefonoPersonal = telefonoPersonal;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getContactoEmergencia() {
        return contactoEmergencia;
    }

    public void setContactoEmergencia(String contactoEmergencia) {
        this.contactoEmergencia = contactoEmergencia;
    }

    public String getTelefonoContacto() {
        return telefonoContacto;
    }

    public void setTelefonoContacto(String telefonoContacto) {
        this.telefonoContacto = telefonoContacto;
    }

    @Override
    public String toString() {
        return "CitaMedica{" +
                "motivoConsulta='" + motivoConsulta + '\'' +
                ", descripcionSintomas='" + descripcionSintomas + '\'' +
                ", fechaInicioSintomas='" + fechaInicioSintomas + '\'' +
                ", antecedentesMedicos='" + antecedentesMedicos + '\'' +
                ", medicamentosActuales='" + medicamentosActuales + '\'' +
                ", consultasPrevias='" + consultasPrevias + '\'' +
                ", condicionesCronicas='" + condicionesCronicas + '\'' +
                ", otraInformacion='" + otraInformacion + '\'' +
                ", telefonoPersonal='" + telefonoPersonal + '\'' +
                ", correoElectronico='" + correoElectronico + '\'' +
                ", contactoEmergencia='" + contactoEmergencia + '\'' +
                ", telefonoContacto='" + telefonoContacto + '\'' +
                '}';
    }
}
