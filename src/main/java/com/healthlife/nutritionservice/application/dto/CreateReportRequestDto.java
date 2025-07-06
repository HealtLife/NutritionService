package com.healthlife.nutritionservice.application.dto;

public class CreateReportRequestDto {

    private String titulo;
    private String userDni;

    private boolean allergies;
    private boolean notes;
    private boolean info;
    private boolean prescription;
    private boolean vaccine;
    private boolean weightHeight;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }


    public boolean isAllergies() {
        return allergies;
    }

    public void setAllergies(boolean allergies) {
        this.allergies = allergies;
    }

    public boolean isNotes() {
        return notes;
    }

    public void setNotes(boolean notes) {
        this.notes = notes;
    }

    public boolean isInfo() {
        return info;
    }

    public void setInfo(boolean info) {
        this.info = info;
    }

    public boolean isPrescription() {
        return prescription;
    }

    public void setPrescription(boolean prescription) {
        this.prescription = prescription;
    }

    public boolean isVaccine() {
        return vaccine;
    }

    public void setVaccine(boolean vaccine) {
        this.vaccine = vaccine;
    }

    public boolean isWeightHeight() {
        return weightHeight;
    }

    public void setWeightHeight(boolean weightHeight) {
        this.weightHeight = weightHeight;
    }

    public String getUserDni() {
        return userDni;
    }

    public void setUserDni(String userDni) {
        this.userDni = userDni;
    }
}
