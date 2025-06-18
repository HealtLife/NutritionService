package com.healthlife.nutritionservice.application.mapper;

import com.healthlife.nutritionservice.application.dto.AllergyDto;
import com.healthlife.nutritionservice.domain.model.aggregates.Allergy;
import org.springframework.stereotype.Component;

@Component
public class AllergyMapper {
    public Allergy toEntity(AllergyDto dto){
        return new Allergy(
                dto.getDni(),
                dto.getAlergia(),
                dto.getReaccion()
        );
    }

    public AllergyDto toDto(Allergy entity) {
        AllergyDto dto = new AllergyDto();
        dto.setDni(entity.getDni());
        dto.setAlergia(entity.getAlergia());
        dto.setReaccion(entity.getReaccion());
        return dto;
    }
}