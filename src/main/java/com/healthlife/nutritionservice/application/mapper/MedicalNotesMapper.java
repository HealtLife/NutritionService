package com.healthlife.nutritionservice.application.mapper;

import com.healthlife.nutritionservice.application.dto.MedicalNotesDto;
import com.healthlife.nutritionservice.domain.model.aggregates.MedicalNotes;
import org.springframework.stereotype.Component;

@Component
public class MedicalNotesMapper {
    public MedicalNotes toEntity(MedicalNotesDto dto) {
        return new MedicalNotes(
                dto.getDni(),
                dto.getNotes(),
                dto.getAutor(),
                dto.getFecha_nota()
        );
    }

    public MedicalNotesDto toDto(MedicalNotes entity) {
        MedicalNotesDto dto = new MedicalNotesDto();
        dto.setDni(entity.getDni());
        dto.setNotes(entity.getNotes());
        dto.setAutor(entity.getAutor());
        dto.setFecha_nota(entity.getFecha_nota() != null ?
                java.time.LocalDate.parse(entity.getFecha_nota()) : null);
        return dto;
    }
}