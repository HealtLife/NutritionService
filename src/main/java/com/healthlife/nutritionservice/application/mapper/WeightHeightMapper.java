package com.healthlife.nutritionservice.application.mapper;

import com.healthlife.nutritionservice.application.dto.WeightHeightDto;
import com.healthlife.nutritionservice.domain.model.aggregates.WeightHeight;
import org.springframework.stereotype.Component;

@Component
public class WeightHeightMapper {
    public WeightHeight toEntity(WeightHeightDto dto){
        return new WeightHeight(
                dto.getDni(),
                dto.getPeso(),
                dto.getAltura(),
                dto.getFechaRegistro()
        );
    }

    public WeightHeightDto toDto(WeightHeight entity) {
        WeightHeightDto dto = new WeightHeightDto();
        dto.setDni(entity.getDni());
        dto.setPeso(entity.getPeso());
        dto.setAltura(entity.getAltura());
        dto.setFechaRegistro(entity.getFechaRegistro());
        return dto;
    }
}