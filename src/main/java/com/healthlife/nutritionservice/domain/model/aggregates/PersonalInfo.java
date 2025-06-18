package com.healthlife.nutritionservice.domain.model.aggregates;

import jakarta.persistence.*;
import java.time.LocalDate;


public class PersonalInfo {

    private String dni;

    private LocalDate fechaNacimiento;

    private String genero;

    private String tipoCuerpo;

    private Double imc;

    public PersonalInfo() {}

    public PersonalInfo(String dni, LocalDate fechaNacimiento, String genero, String tipoCuerpo, Double imc) {
        this.dni = dni;
        this.fechaNacimiento = fechaNacimiento;
        this.genero = genero;
        this.tipoCuerpo = tipoCuerpo;
        this.imc = imc;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getTipoCuerpo() {
        return tipoCuerpo;
    }

    public void setTipoCuerpo(String tipoCuerpo) {
        this.tipoCuerpo = tipoCuerpo;
    }

    public Double getImc() {
        return imc;
    }

    public void setImc(Double imc) {
        this.imc = imc;
    }
}