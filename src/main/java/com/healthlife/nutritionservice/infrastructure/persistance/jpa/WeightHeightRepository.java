package com.healthlife.nutritionservice.infrastructure.persistance.jpa;

import com.healthlife.nutritionservice.domain.model.aggregates.WeightHeight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeightHeightRepository extends JpaRepository<WeightHeight,String> {
    List<WeightHeight> findByDni(String dni);
}