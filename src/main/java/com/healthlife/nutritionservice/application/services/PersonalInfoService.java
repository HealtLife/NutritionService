package com.healthlife.nutritionservice.application.services;

import com.healthlife.nutritionservice.domain.model.aggregates.PersonalInfo;
import com.healthlife.nutritionservice.application.dto.PersonalInfoDto;
import com.healthlife.nutritionservice.application.mapper.PersonalInfoMapper;
import com.healthlife.nutritionservice.infrastructure.persistance.jpa.PersonalInfoRepository;
import com.healthlife.nutritionservice.infrastructure.external.MedicalHistoryClient;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonalInfoService {
    private final PersonalInfoRepository repository;
    private final PersonalInfoMapper mapper;
    private final MedicalHistoryClient medicalHistoryClient;

    public PersonalInfoService(PersonalInfoRepository repository, PersonalInfoMapper mapper, MedicalHistoryClient medicalHistoryClient) {
        this.repository = repository;
        this.mapper = mapper;
        this.medicalHistoryClient = medicalHistoryClient;
    }

    @Transactional
    public void savePersonalInfo(PersonalInfoDto dto) {
        Optional<PersonalInfo> existing = repository.findById(dto.getDni());
        if (existing.isPresent()) {
            throw new IllegalArgumentException("El paciente con DNI " + dto.getDni() + " ya existe.");
        }
        PersonalInfo entity = mapper.toEntity(dto);
        repository.save(entity);
    }

    @Transactional
    public void syncPersonalInfo(String dni) {
        try {
            PersonalInfoDto dto = medicalHistoryClient.getPersonalInfo(dni);
            PersonalInfo entity = mapper.toEntity(dto);
            repository.save(entity);
        } catch (Exception e) {
            throw new RuntimeException("Error sincronizando información personal para DNI: " + dni, e);
        }
    }

    public PersonalInfoDto getPersonalInfoByDni(String dni) {
        PersonalInfo entity = repository.findById(dni)
                .orElseThrow(() -> new EntityNotFoundException("Paciente con DNI " + dni + " no encontrado"));
        return mapper.toDto(entity);
    }

    @Transactional
    public void updatePersonalInfo(String dni, PersonalInfoDto dto) {
        PersonalInfo existing = repository.findById(dni)
                .orElseThrow(() -> new EntityNotFoundException("Paciente con DNI " + dni + " no encontrado"));

        existing.setFechaNacimiento(dto.getFechaNacimiento());
        existing.setGenero(dto.getGenero());
        existing.setTipoCuerpo(dto.getTipoCuerpo());
        existing.setImc(dto.getImc());

        repository.save(existing);
    }
}