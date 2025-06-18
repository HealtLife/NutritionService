package com.healthlife.nutritionservice.application.mapper;

import com.healthlife.nutritionservice.domain.model.aggregates.PersonalInfo;
import com.healthlife.nutritionservice.application.dto.PersonalInfoDto;
import org.springframework.stereotype.Component;

@Component
public class PersonalInfoMapper {
    public PersonalInfo toEntity(PersonalInfoDto dto) {
        return new PersonalInfo(
                dto.getDni(),
                dto.getFechaNacimiento(),
                dto.getGenero(),
                dto.getTipoCuerpo(),
                dto.getImc()
        );
    }

    public PersonalInfoDto toDto(PersonalInfo entity) {
        PersonalInfoDto dto = new PersonalInfoDto();
        dto.setDni(entity.getDni());
        dto.setFechaNacimiento(entity.getFechaNacimiento());
        dto.setGenero(entity.getGenero());
        dto.setTipoCuerpo(entity.getTipoCuerpo());
        dto.setImc(entity.getImc());
        return dto;
    }
}