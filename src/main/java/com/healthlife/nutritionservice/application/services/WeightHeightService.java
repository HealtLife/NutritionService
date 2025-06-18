package com.healthlife.nutritionservice.application.services;

import com.healthlife.nutritionservice.infrastructure.persistance.jpa.WeightHeightRepository;
import com.healthlife.nutritionservice.application.dto.WeightHeightDto;
import com.healthlife.nutritionservice.application.mapper.WeightHeightMapper;
import com.healthlife.nutritionservice.domain.model.aggregates.WeightHeight;
import com.healthlife.nutritionservice.infrastructure.external.MedicalHistoryClient;
import com.healthlife.nutritionservice.infrastructure.persistance.jpa.PersonalInfoRepository;
import com.healthlife.nutritionservice.domain.model.aggregates.PersonalInfo;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WeightHeightService {
    private final WeightHeightRepository weightHeightRepository;
    private final WeightHeightMapper mapper;
    private final MedicalHistoryClient medicalHistoryClient;
    private final PersonalInfoRepository personalInfoRepository;

    public WeightHeightService(WeightHeightRepository weightHeightRepository, WeightHeightMapper mapper,
                               MedicalHistoryClient medicalHistoryClient, PersonalInfoRepository personalInfoRepository) {
        this.weightHeightRepository = weightHeightRepository;
        this.mapper = mapper;
        this.medicalHistoryClient = medicalHistoryClient;
        this.personalInfoRepository = personalInfoRepository;
    }

    @Transactional
    public void saveWeightHeight(WeightHeightDto dto) {
        Optional<PersonalInfo> patient = personalInfoRepository.findById(dto.getDni());
        if (patient.isEmpty()) {
            throw new IllegalArgumentException("No existe un paciente con DNI " + dto.getDni());
        }
        WeightHeight entity = mapper.toEntity(dto);
        weightHeightRepository.save(entity);
    }

    @Transactional
    public void syncWeightHeight(String dni) {
        try {
            List<WeightHeightDto> dtos = medicalHistoryClient.getWeightHeight(dni);

            // Clear existing weight/height records for this patient
            List<WeightHeight> existingRecords = weightHeightRepository.findByDni(dni);
            weightHeightRepository.deleteAll(existingRecords);

            // Save new records
            for (WeightHeightDto dto : dtos) {
                WeightHeight entity = mapper.toEntity(dto);
                weightHeightRepository.save(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error sincronizando peso/altura para DNI: " + dni, e);
        }
    }

    public List<WeightHeightDto> getWeightHeightByDni(String dni) {
        List<WeightHeight> records = weightHeightRepository.findByDni(dni);
        if (records.isEmpty()) {
            throw new EntityNotFoundException("Paciente con DNI " + dni + " no tiene registros de peso/altura.");
        }
        return records.stream()
                .map(mapper::toDto)
                .toList();
    }
}