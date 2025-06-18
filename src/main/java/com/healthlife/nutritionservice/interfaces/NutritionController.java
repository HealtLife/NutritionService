package com.healthlife.nutritionservice.interfaces;

import com.healthlife.nutritionservice.application.dto.*;
import com.healthlife.nutritionservice.application.services.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/nutrition", produces = APPLICATION_JSON_VALUE)
public class NutritionController {
    private final PersonalInfoService personalInfoService;
    private final MedicalNotesService medicalNotesService;
    private final AllergyService allergyService;
    private final PrescriptionService prescriptionService;
    private final VaccineService vaccineService;
    private final WeightHeightService weightHeightService;

    public NutritionController(PersonalInfoService personalInfoService, MedicalNotesService medicalNotesService,
                               AllergyService allergyService, PrescriptionService prescriptionService,
                               VaccineService vaccineService, WeightHeightService weightHeightService) {
        this.personalInfoService = personalInfoService;
        this.medicalNotesService = medicalNotesService;
        this.allergyService = allergyService;
        this.prescriptionService = prescriptionService;
        this.vaccineService = vaccineService;
        this.weightHeightService = weightHeightService;
    }

    // SYNC ENDPOINTS (Feign Client calls)
    @PostMapping("/sync/{dni}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> syncAllMedicalData(@PathVariable String dni) {
        try {
            personalInfoService.syncPersonalInfo(dni);
            allergyService.syncAllergies(dni);
            prescriptionService.syncPrescriptions(dni);
            vaccineService.syncVaccines(dni);
            weightHeightService.syncWeightHeight(dni);
            return ResponseEntity.ok("Datos sincronizados correctamente para DNI: " + dni);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body("Error sincronizando datos: " + e.getMessage());
        }
    }

    // PERSONAL INFO ENDPOINTS
    @PostMapping("/personal-info")
    @ResponseStatus(HttpStatus.CREATED)
    public void savePersonalInfo(@RequestBody PersonalInfoDto dto) {
        personalInfoService.savePersonalInfo(dto);
    }

    @PutMapping("/personal-info/{dni}")
    public void updatePersonalInfo(@PathVariable String dni, @RequestBody PersonalInfoDto dto) {
        personalInfoService.updatePersonalInfo(dni, dto);
    }

    @GetMapping("/personal-info/{dni}")
    public ResponseEntity<PersonalInfoDto> getPersonalInfo(@PathVariable String dni) {
        PersonalInfoDto dto = personalInfoService.getPersonalInfoByDni(dni);
        return ResponseEntity.ok(dto);
    }

    // MEDICAL NOTES ENDPOINTS
    @PostMapping("/medical-notes")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveMedicalNote(@RequestBody MedicalNotesDto dto) {
        medicalNotesService.saveMedicalNote(dto);
    }

    @GetMapping("/medical-notes/{dni}")
    public ResponseEntity<List<MedicalNotesDto>> getMedicalNotes(@PathVariable String dni) {
        List<MedicalNotesDto> dtos = medicalNotesService.getMedicalNotesByDni(dni);
        return ResponseEntity.ok(dtos);
    }

    // ALLERGIES ENDPOINTS
    @PostMapping("/allergies")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveAllergy(@RequestBody AllergyDto dto) {
        allergyService.saveAllergy(dto);
    }

    @GetMapping("/allergies/{dni}")
    public ResponseEntity<List<AllergyDto>> getAllergies(@PathVariable String dni) {
        List<AllergyDto> dtos = allergyService.getAllergiesByDni(dni);
        return ResponseEntity.ok(dtos);
    }

    // PRESCRIPTIONS ENDPOINTS
    @PostMapping("/prescriptions")
    @ResponseStatus(HttpStatus.CREATED)
    public void savePrescription(@RequestBody PrescriptionDto dto) {
        prescriptionService.savePrescription(dto);
    }

    @GetMapping("/prescriptions/{dni}")
    public ResponseEntity<List<PrescriptionDto>> getPrescriptions(@PathVariable String dni) {
        List<PrescriptionDto> dtos = prescriptionService.getPrescriptionByDni(dni);
        return ResponseEntity.ok(dtos);
    }

    // VACCINES ENDPOINTS
    @PostMapping("/vaccines")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveVaccine(@RequestBody VaccineDto dto) {
        vaccineService.saveVaccine(dto);
    }

    @GetMapping("/vaccines/{dni}")
    public ResponseEntity<List<VaccineDto>> getVaccines(@PathVariable String dni) {
        List<VaccineDto> dtos = vaccineService.getVaccineByDni(dni);
        return ResponseEntity.ok(dtos);
    }

    // WEIGHT HEIGHT ENDPOINTS
    @PostMapping("/weight-height")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveWeightHeight(@RequestBody WeightHeightDto dto) {
        weightHeightService.saveWeightHeight(dto);
    }

    @GetMapping("/weight-height/{dni}")
    public ResponseEntity<List<WeightHeightDto>> getWeightHeight(@PathVariable String dni) {
        List<WeightHeightDto> dtos = weightHeightService.getWeightHeightByDni(dni);
        return ResponseEntity.ok(dtos);
    }
}