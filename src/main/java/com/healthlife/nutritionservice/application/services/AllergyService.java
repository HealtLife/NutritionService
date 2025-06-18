package com.healthlife.nutritionservice.application.services;

import com.healthlife.nutritionservice.infrastructure.persistance.jpa.AllergyRepository;
import com.healthlife.nutritionservice.application.dto.AllergyDto;
import com.healthlife.nutritionservice.application.mapper.AllergyMapper;
import com.healthlife.nutritionservice.domain.model.aggregates.Allergy;
import com.healthlife.nutritionservice.infrastructure.external.MedicalHistoryClient;
import com.healthlife.nutritionservice.infrastructure.persistance.jpa.PersonalInfoRepository;
import com.healthlife.nutritionservice.domain.model.aggregates.PersonalInfo;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AllergyService {
    private final AllergyRepository allergyRepository;
    private final AllergyMapper mapper;
    private final MedicalHistoryClient medicalHistoryClient;
    private final PersonalInfoRepository personalInfoRepository;

    public AllergyService(AllergyRepository allergyRepository, AllergyMapper mapper,
                          MedicalHistoryClient medicalHistoryClient, PersonalInfoRepository personalInfoRepository) {
        this.allergyRepository = allergyRepository;
        this.mapper = mapper;
        this.medicalHistoryClient = medicalHistoryClient;
        this.personalInfoRepository = personalInfoRepository;
    }

    @Transactional
    public void saveAllergy(AllergyDto dto) {
        Optional<PersonalInfo> patient = personalInfoRepository.findById(dto.getDni());
        if (patient.isEmpty()) {
            throw new IllegalArgumentException("No existe un paciente con DNI " + dto.getDni());
        }
        Allergy allergy = mapper.toEntity(dto);
        allergyRepository.save(allergy);
    }

    @Transactional
    public void syncAllergies(String dni) {
        try {
            List<AllergyDto> dtos = medicalHistoryClient.getAllergies(dni);

            // Clear existing allergies for this patient
            List<Allergy> existingAllergies = allergyRepository.findByDni(dni);
            allergyRepository.deleteAll(existingAllergies);

            // Save new allergies
            for (AllergyDto dto : dtos) {
                Allergy entity = mapper.toEntity(dto);
                allergyRepository.save(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error sincronizando alergias para DNI: " + dni, e);
        }
    }

    public List<AllergyDto> getAllergiesByDni(String dni) {
        List<Allergy> allergies = allergyRepository.findByDni(dni);
        if (allergies.isEmpty()) {
            throw new EntityNotFoundException("Paciente con DNI " + dni + " no tiene alergias registradas.");
        }
        return allergies.stream()
                .map(mapper::toDto)
                .toList();
    }
}