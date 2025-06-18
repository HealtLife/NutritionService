package com.healthlife.nutritionservice.infrastructure.external;

import com.healthlife.nutritionservice.application.dto.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "MedicalHistoryService")
public interface MedicalHistoryClient {

    @GetMapping("/api/v1/medical-history/personal-info/{dni}")
    PersonalInfoDto getPersonalInfo(@PathVariable String dni);

    @GetMapping("/api/v1/medical-history/allergies/{dni}")
    List<AllergyDto> getAllergies(@PathVariable String dni);

    @GetMapping("/api/v1/medical-history/prescription/{dni}")
    List<PrescriptionDto> getPrescriptions(@PathVariable String dni);

    @GetMapping("/api/v1/medical-history/vaccine/{dni}")
    List<VaccineDto> getVaccines(@PathVariable String dni);

    @GetMapping("/api/v1/medical-history/weightheight/{dni}")
    List<WeightHeightDto> getWeightHeight(@PathVariable String dni);
}