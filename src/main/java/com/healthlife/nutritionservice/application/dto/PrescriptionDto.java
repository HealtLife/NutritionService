package com.healthlife.nutritionservice.application.dto;

import java.time.LocalDate;

public class PrescriptionDto {
    private String dni;
    private String prescripcion;
    private LocalDate fecha_receta;
    private String medico;

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getPrescripcion() {
        return prescripcion;
    }

    public void setPrescripcion(String prescripcion) {
        this.prescripcion = prescripcion;
    }

    public LocalDate getFecha_receta() {
        return fecha_receta;
    }

    public void setFecha_receta(LocalDate fecha_receta) {
        this.fecha_receta = fecha_receta;
    }

    public String getMedico() {
        return medico;
    }

    public void setMedico(String medico) {
        this.medico = medico;
    }
}