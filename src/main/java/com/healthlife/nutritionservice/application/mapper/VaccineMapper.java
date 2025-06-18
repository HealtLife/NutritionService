package com.healthlife.nutritionservice.application.mapper;

import com.healthlife.nutritionservice.application.dto.VaccineDto;
import com.healthlife.nutritionservice.domain.model.aggregates.Vaccine;
import org.springframework.stereotype.Component;

@Component
public class VaccineMapper {
    public Vaccine toEntity(VaccineDto dto){
        return new Vaccine(
                dto.getDni(),
                dto.getVacuna(),
                dto.getFechaAplicacion(),
                dto.getDosis()
        );
    }

    public VaccineDto toDto(Vaccine entity) {
        VaccineDto dto = new VaccineDto();
        dto.setDni(entity.getDni());
        dto.setVacuna(entity.getVacuna());
        dto.setFechaAplicacion(entity.getFechaAplicacion());
        dto.setDosis(entity.getDosis());
        return dto;
    }
}