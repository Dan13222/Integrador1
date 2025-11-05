package com.example.clinica;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Medico {

    @Id
    @GeneratedValue(generator="sec_medico") 
    @SequenceGenerator(name="sec_medico", sequenceName="sec_medico", allocationSize=1)
    private int id;
    private String nombre;
    private String apellido;
    private String telefono;
    private String correoElectronico;
    private String especialidadMedica;
    private String dni;
    private String numero;
    private String requerimientosMedicos;
    private String fechaHoraDisponibilidad;

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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
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

    public String getEspecialidadMedica() {
        return especialidadMedica;
    }

    public void setEspecialidadMedica(String especialidadMedica) {
        this.especialidadMedica = especialidadMedica;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getRequerimientosMedicos() {
        return requerimientosMedicos;
    }

    public void setRequerimientosMedicos(String requerimientosMedicos) {
        this.requerimientosMedicos = requerimientosMedicos;
    }

    public String getFechaHoraDisponibilidad() {
        return fechaHoraDisponibilidad;
    }

    public void setFechaHoraDisponibilidad(String fechaHoraDisponibilidad) {
        this.fechaHoraDisponibilidad = fechaHoraDisponibilidad;
    }
}
