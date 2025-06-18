package com.healthlife.nutritionservice.infrastructure.persistance.jpa;

import com.healthlife.nutritionservice.domain.model.aggregates.PersonalInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalInfoRepository extends JpaRepository<PersonalInfo, String> {
}