package com.example.clinica;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
public class ReporteError {

    @Id
    @GeneratedValue(generator="sec_reporte_error") 
    @SequenceGenerator(name="sec_reporte_error", sequenceName="sec_reporte_error", allocationSize=1)
    private int id;

    private String nombrePaciente;
    private String correoElectronico;
    private String errorSistema;
    private String infoAdicional;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

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

    public String getErrorSistema() {
        return errorSistema;
    }
    public void setErrorSistema(String errorSistema) {
        this.errorSistema = errorSistema;
    }

    public String getInfoAdicional() {
        return infoAdicional;
    }
    public void setInfoAdicional(String infoAdicional) {
        this.infoAdicional = infoAdicional;
    }
}
