package com.healthlife.nutritionservice.application.mapper;

import com.healthlife.nutritionservice.application.dto.PrescriptionDto;
import com.healthlife.nutritionservice.domain.model.aggregates.Prescription;
import org.springframework.stereotype.Component;

@Component
public class PrescriptionMapper {
    public Prescription toEntity(PrescriptionDto dto){
        return new Prescription(
                dto.getDni(),
                dto.getPrescripcion(),
                dto.getFecha_receta(),
                dto.getMedico()
        );
    }

    public PrescriptionDto toDto(Prescription entity) {
        PrescriptionDto dto = new PrescriptionDto();
        dto.setDni(entity.getDni());
        dto.setPrescripcion(entity.getPrescripcion());
        dto.setFecha_receta(entity.getFechaReceta());
        dto.setMedico(entity.getMedico());
        return dto;
    }
}