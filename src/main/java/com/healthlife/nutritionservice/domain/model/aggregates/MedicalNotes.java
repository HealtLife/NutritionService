package com.healthlife.nutritionservice.domain.model.aggregates;

import jakarta.persistence.*;
import java.time.LocalDate;


public class MedicalNotes {

    private int id;

    private String dni;
    private String fecha_nota;
    private String notes;
    private String autor;

    public MedicalNotes(String dni, String notes, String autor, LocalDate fechaNota) {
        this.dni = dni;
        this.fecha_nota = fechaNota.toString();
        this.notes = notes;
        this.autor = autor;
    }

    public MedicalNotes() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getFecha_nota() {
        return fecha_nota;
    }

    public void setFecha_nota(String fecha_nota) {
        this.fecha_nota = fecha_nota;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
}