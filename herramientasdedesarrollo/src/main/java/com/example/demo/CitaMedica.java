package com.example.clinica;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
public class CitaMedica {

    @Id
    @GeneratedValue(generator = "sec_citas")
    @SequenceGenerator(name = "sec_citas", sequenceName = "sec_citas", allocationSize = 1)
    private int id;

    private String nombre;
    private String apellido;
    private String fechaInicioSintomas;
    private String descripcionSintomas;
    private String dni;
    private String discapacidad;
    private String condicionesCronicas;
    private String otraInformacion;
    private String telefonoPersonal;
    private String correoElectronico;
    private String antecedentes;
    private String telefonoContacto;

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getFechaInicioSintomas() { return fechaInicioSintomas; }
    public void setFechaInicioSintomas(String fechaInicioSintomas) { this.fechaInicioSintomas = fechaInicioSintomas; }

    public String getDescripcionSintomas() { return descripcionSintomas; }
    public void setDescripcionSintomas(String descripcionSintomas) { this.descripcionSintomas = descripcionSintomas; }

    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }

    public String getDiscapacidad() { return discapacidad; }
    public void setDiscapacidad(String discapacidad) { this.discapacidad = discapacidad; }

    public String getCondicionesCronicas() { return condicionesCronicas; }
    public void setCondicionesCronicas(String condicionesCronicas) { this.condicionesCronicas = condicionesCronicas; }

    public String getOtraInformacion() { return otraInformacion; }
    public void setOtraInformacion(String otraInformacion) { this.otraInformacion = otraInformacion; }

    public String getTelefonoPersonal() { return telefonoPersonal; }
    public void setTelefonoPersonal(String telefonoPersonal) { this.telefonoPersonal = telefonoPersonal; }

    public String getCorreoElectronico() { return correoElectronico; }
    public void setCorreoElectronico(String correoElectronico) { this.correoElectronico = correoElectronico; }

    public String getAntecedentes() { return antecedentes; }
    public void setAntecedentes(String antecedentes) { this.antecedentes = antecedentes; }

    public String getTelefonoContacto() { return telefonoContacto; }
    public void setTelefonoContacto(String telefonoContacto) { this.telefonoContacto = telefonoContacto; }
}
