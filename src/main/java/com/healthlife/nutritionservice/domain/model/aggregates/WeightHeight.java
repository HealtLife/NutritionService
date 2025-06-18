package com.healthlife.nutritionservice.domain.model.aggregates;

import jakarta.persistence.*;
import java.time.LocalDate;


public class WeightHeight {

    private Long id;

    private String dni;
    private Double peso;
    private Double altura;

    private LocalDate fechaRegistro;

    public WeightHeight() {
    }

    public WeightHeight(String dni, Double peso, Double altura, LocalDate fechaRegistro) {
        this.dni = dni;
        this.peso = peso;
        this.altura = altura;
        this.fechaRegistro = fechaRegistro;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Double getAltura() {
        return altura;
    }

    public void setAltura(Double altura) {
        this.altura = altura;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}