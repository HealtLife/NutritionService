package com.healthlife.nutritionservice.infrastructure.persistance.jpa;

import com.healthlife.nutritionservice.domain.model.aggregates.Allergy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AllergyRepository extends JpaRepository<Allergy, String> {
    List<Allergy> findByDni(String dni);
}