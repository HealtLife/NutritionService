package com.healthlife.nutritionservice.infrastructure.persistance.jpa;

import com.healthlife.nutritionservice.domain.model.aggregates.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VaccineRepository extends JpaRepository<Vaccine, String> {
    List<Vaccine> findByDni(String dni);
}