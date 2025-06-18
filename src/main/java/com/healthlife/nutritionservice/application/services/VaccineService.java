package com.healthlife.nutritionservice.application.services;

import com.healthlife.nutritionservice.infrastructure.persistance.jpa.VaccineRepository;
import com.healthlife.nutritionservice.application.dto.VaccineDto;
import com.healthlife.nutritionservice.application.mapper.VaccineMapper;
import com.healthlife.nutritionservice.domain.model.aggregates.Vaccine;
import com.healthlife.nutritionservice.infrastructure.external.MedicalHistoryClient;
import com.healthlife.nutritionservice.infrastructure.persistance.jpa.PersonalInfoRepository;
import com.healthlife.nutritionservice.domain.model.aggregates.PersonalInfo;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VaccineService {
    private final VaccineRepository vaccineRepository;
    private final VaccineMapper mapper;
    private final MedicalHistoryClient medicalHistoryClient;
    private final PersonalInfoRepository personalInfoRepository;

    public VaccineService(VaccineRepository vaccineRepository, VaccineMapper mapper,
                          MedicalHistoryClient medicalHistoryClient, PersonalInfoRepository personalInfoRepository) {
        this.vaccineRepository = vaccineRepository;
        this.mapper = mapper;
        this.medicalHistoryClient = medicalHistoryClient;
        this.personalInfoRepository = personalInfoRepository;
    }

    @Transactional
    public void saveVaccine(VaccineDto dto) {
        Optional<PersonalInfo> patient = personalInfoRepository.findById(dto.getDni());
        if (patient.isEmpty()) {
            throw new IllegalArgumentException("No existe un paciente con DNI " + dto.getDni());
        }
        Vaccine entity = mapper.toEntity(dto);
        vaccineRepository.save(entity);
    }

    @Transactional
    public void syncVaccines(String dni) {
        try {
            List<VaccineDto> dtos = medicalHistoryClient.getVaccines(dni);

            // Clear existing vaccines for this patient
            List<Vaccine> existingVaccines = vaccineRepository.findByDni(dni);
            vaccineRepository.deleteAll(existingVaccines);

            // Save new vaccines
            for (VaccineDto dto : dtos) {
                Vaccine entity = mapper.toEntity(dto);
                vaccineRepository.save(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error sincronizando vacunas para DNI: " + dni, e);
        }
    }

    public List<VaccineDto> getVaccineByDni(String dni) {
        List<Vaccine> vaccines = vaccineRepository.findByDni(dni);
        if (vaccines.isEmpty()) {
            throw new EntityNotFoundException("Paciente con DNI " + dni + " no tiene vacunas registradas.");
        }
        return vaccines.stream()
                .map(mapper::toDto)
                .toList();
    }
}