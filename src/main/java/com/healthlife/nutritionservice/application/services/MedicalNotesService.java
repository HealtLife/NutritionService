package com.healthlife.nutritionservice.application.services;

import com.healthlife.nutritionservice.application.dto.MedicalNotesDto;
import com.healthlife.nutritionservice.application.mapper.MedicalNotesMapper;
import com.healthlife.nutritionservice.domain.model.aggregates.MedicalNotes;
import com.healthlife.nutritionservice.infrastructure.persistance.jpa.MedicalNotesRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalNotesService {
    private final MedicalNotesRepository medicalNotesRepository;
    private final MedicalNotesMapper mapper;

    public MedicalNotesService(MedicalNotesRepository medicalNotesRepository, MedicalNotesMapper mapper) {
        this.medicalNotesRepository = medicalNotesRepository;
        this.mapper = mapper;
    }

    @Transactional
    public void saveMedicalNote(MedicalNotesDto dto) {
        MedicalNotes note = mapper.toEntity(dto);
        medicalNotesRepository.save(note);
    }

    public List<MedicalNotesDto> getMedicalNotesByDni(String dni) {
        List<MedicalNotes> medical = medicalNotesRepository.findByDni(dni);
        if (medical.isEmpty()) {
            throw new EntityNotFoundException("Paciente con DNI " + dni + " no tiene notas médicas registradas.");
        }
        return medical.stream()
                .map(mapper::toDto)
                .toList();
    }
}