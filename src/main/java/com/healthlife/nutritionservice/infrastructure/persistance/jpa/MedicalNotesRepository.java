package com.healthlife.nutritionservice.infrastructure.persistance.jpa;

import com.healthlife.nutritionservice.domain.model.aggregates.MedicalNotes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicalNotesRepository extends JpaRepository<MedicalNotes, String> {
    List<MedicalNotes> findByDni(String dni);
}