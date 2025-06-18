package com.healthlife.nutritionservice.application.services;

import com.healthlife.nutritionservice.infrastructure.persistance.jpa.PrescriptionRepository;
import com.healthlife.nutritionservice.application.dto.PrescriptionDto;
import com.healthlife.nutritionservice.domain.model.aggregates.Prescription;
import com.healthlife.nutritionservice.application.mapper.PrescriptionMapper;
import com.healthlife.nutritionservice.infrastructure.external.MedicalHistoryClient;
import com.healthlife.nutritionservice.infrastructure.persistance.jpa.PersonalInfoRepository;
import com.healthlife.nutritionservice.domain.model.aggregates.PersonalInfo;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrescriptionService {
    private final PrescriptionRepository prescriptionRepository;
    private final PrescriptionMapper mapper;
    private final MedicalHistoryClient medicalHistoryClient;
    private final PersonalInfoRepository personalInfoRepository;

    public PrescriptionService(PrescriptionRepository prescriptionRepository, PrescriptionMapper mapper,
                               MedicalHistoryClient medicalHistoryClient, PersonalInfoRepository personalInfoRepository) {
        this.prescriptionRepository = prescriptionRepository;
        this.mapper = mapper;
        this.medicalHistoryClient = medicalHistoryClient;
        this.personalInfoRepository = personalInfoRepository;
    }

    @Transactional
    public void savePrescription(PrescriptionDto dto) {
        Optional<PersonalInfo> patient = personalInfoRepository.findById(dto.getDni());
        if (patient.isEmpty()) {
            throw new IllegalArgumentException("No existe un paciente con DNI " + dto.getDni());
        }
        Prescription prescription = mapper.toEntity(dto);
        prescriptionRepository.save(prescription);
    }

    @Transactional
    public void syncPrescriptions(String dni) {
        try {
            List<PrescriptionDto> dtos = medicalHistoryClient.getPrescriptions(dni);

            // Clear existing prescriptions for this patient
            List<Prescription> existingPrescriptions = prescriptionRepository.findByDni(dni);
            prescriptionRepository.deleteAll(existingPrescriptions);

            // Save new prescriptions
            for (PrescriptionDto dto : dtos) {
                Prescription entity = mapper.toEntity(dto);
                prescriptionRepository.save(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error sincronizando prescripciones para DNI: " + dni, e);
        }
    }

    public List<PrescriptionDto> getPrescriptionByDni(String dni) {
        List<Prescription> prescriptions = prescriptionRepository.findByDni(dni);
        if (prescriptions.isEmpty()) {
            throw new EntityNotFoundException("Paciente con DNI " + dni + " no tiene prescripciones registradas.");
        }
        return prescriptions.stream()
                .map(mapper::toDto)
                .toList();
    }
}