package com.healthlife.nutritionservice.domain.model.aggregates;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "vaccines")
public class Vaccine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dni;
    private String vacuna;

    @Column(name = "fecha_aplicacion")
    private LocalDate fechaAplicacion;

    private String dosis;

    public Vaccine() {
    }

    public Vaccine(String dni, String vacuna, LocalDate fechaAplicacion, String dosis) {
        this.dni = dni;
        this.vacuna = vacuna;
        this.fechaAplicacion = fechaAplicacion;
        this.dosis = dosis;
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

    public String getVacuna() {
        return vacuna;
    }

    public void setVacuna(String vacuna) {
        this.vacuna = vacuna;
    }

    public LocalDate getFechaAplicacion() {
        return fechaAplicacion;
    }

    public void setFechaAplicacion(LocalDate fechaAplicacion) {
        this.fechaAplicacion = fechaAplicacion;
    }

    public String getDosis() {
        return dosis;
    }

    public void setDosis(String dosis) {
        this.dosis = dosis;
    }
}