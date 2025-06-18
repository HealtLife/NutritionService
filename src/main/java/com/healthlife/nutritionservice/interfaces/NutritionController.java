package com.healthlife.nutritionservice.interfaces;

import com.healthlife.nutritionservice.application.dto.*;

import com.healthlife.nutritionservice.domain.model.aggregates.Equipments;
import com.healthlife.nutritionservice.domain.model.aggregates.Exercise;
import com.healthlife.nutritionservice.domain.model.aggregates.Muscle;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/nutrition")
public class NutritionController {
    private final WebClient webClientMH;
    private final WebClient webClientFApi;

    public NutritionController() {
        this.webClientMH = WebClient.builder()
                .baseUrl("http://localhost:8086/api/v1/medical-history")
                .build();

        this.webClientFApi = WebClient.builder()
                .baseUrl("http://localhost:8085/api/v1/fitnessapiservice")
                .build();
    }
    @GetMapping("/personal-info/{dni}")
    public ResponseEntity<?> getPersonalInfo(@PathVariable String dni) {
        PersonalInfoDto dto = webClientMH.get()
                .uri("/personal-info/{dni}", dni)
                .retrieve()
                .bodyToMono(PersonalInfoDto.class)
                .block();

        return ResponseEntity.ok(dto);
    }

    @GetMapping("/medical-note/{dni}")
    public ResponseEntity<?> getMedicalNotes(@PathVariable String dni) {
        List<MedicalNotesDto> dtos = webClientMH.get()
                .uri("/medical-note/{dni}", dni)
                .retrieve()
                .bodyToFlux(MedicalNotesDto.class)
                .collectList() // ✅ Convertimos a List
                .block(); // ❗ Esperamos la lista completa de forma bloqueante (en este ejemplo)

        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/allergies/{dni}")
    public ResponseEntity<?> getAllergy(@PathVariable String dni) {
        List<AllergyDto> dtos = webClientMH.get()
                .uri("/allergies/{dni}", dni)
                .retrieve()
                .bodyToFlux(AllergyDto.class)
                .collectList() // ✅ Convertimos a List
                .block(); // ❗ Esperamos la lista completa de forma bloqueante (en este ejemplo)

        return ResponseEntity.ok(dtos);
    }
    @GetMapping("/prescription/{dni}")
    public ResponseEntity<?> getPrescriptions(@PathVariable String dni) {
        List<PrescriptionDto> dtos = webClientMH.get()
                .uri("/prescription/{dni}", dni)
                .retrieve()
                .bodyToFlux(PrescriptionDto.class)
                .collectList() // ✅ Convertimos a List
                .block(); // ❗ Esperamos la lista completa de forma bloqueante (en este ejemplo)

        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/vaccine/{dni}")
    public ResponseEntity<?> getVaccine(@PathVariable String dni) {
        List<VaccineDto> dtos = webClientMH.get()
                .uri("/vaccine/{dni}", dni)
                .retrieve()
                .bodyToFlux(VaccineDto.class)
                .collectList() // ✅ Convertimos a List
                .block(); // ❗ Esperamos la lista completa de forma bloqueante (en este ejemplo)

        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/weightheight/{dni}")
    public ResponseEntity<?> getWeightHeight(@PathVariable String dni) {
        List<WeightHeightDto> dtos = webClientMH.get()
                .uri("/weightheight/{dni}", dni)
                .retrieve()
                .bodyToFlux(WeightHeightDto.class)
                .collectList() // ✅ Convertimos a List
                .block(); // ❗ Esperamos la lista completa de forma bloqueante (en este ejemplo)

        return ResponseEntity.ok(dtos);
    }

    @PostMapping("/personal-info")
    public ResponseEntity<Void> savePersonalInfo(@RequestBody PersonalInfoDto dto) {
        webClientMH.post()
                .uri("/personal-info")
                .bodyValue(dto)
                .retrieve()
                .toBodilessEntity()
                .block();

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/medical-note")
    public ResponseEntity<Void> saveMedicalNote(@RequestBody MedicalNotesDto dto) {
        webClientMH.post()
                .uri("/medical-note")
                .bodyValue(dto)
                .retrieve()
                .toBodilessEntity()
                .block();

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/allergies")
    public ResponseEntity<Void> saveAllergy(@RequestBody AllergyDto dto) {
        webClientMH.post()
                .uri("/allergies")
                .bodyValue(dto)
                .retrieve()
                .toBodilessEntity()
                .block();

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/prescription")
    public ResponseEntity<Void> savePrescription(@RequestBody PrescriptionDto dto) {
        webClientMH.post()
                .uri("/prescription")
                .bodyValue(dto)
                .retrieve()
                .toBodilessEntity()
                .block();

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/vaccine")
    public ResponseEntity<Void> saveVaccine(@RequestBody VaccineDto dto) {
        webClientMH.post()
                .uri("/vaccine")
                .bodyValue(dto)
                .retrieve()
                .toBodilessEntity()
                .block();

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/weightheight")
    public ResponseEntity<Void> saveWeightHeight(@RequestBody WeightHeightDto dto) {
        webClientMH.post()
                .uri("/weightheight")
                .bodyValue(dto)
                .retrieve()
                .toBodilessEntity()
                .block();

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/personal-info/{dni}")
    public ResponseEntity<Void> updatePersonalInfo(@PathVariable String dni, @RequestBody PersonalInfoDto dto) {
        webClientMH.put()
                .uri("/personal-info/{dni}", dni)
                .bodyValue(dto)
                .retrieve()
                .toBodilessEntity()
                .block();

        return ResponseEntity.ok().build();
    }

    @PutMapping("/medical-note/{id}")
    public ResponseEntity<Void> updateMedicalNote(@PathVariable Long id, @RequestBody MedicalNotesDto dto) {
        webClientMH.put()
                .uri("/medical-note/{id}", id)
                .bodyValue(dto)
                .retrieve()
                .toBodilessEntity()
                .block();

        return ResponseEntity.ok().build();
    }

    @PutMapping("/allergies/{id}")
    public ResponseEntity<Void> updateAllergy(@PathVariable Long id, @RequestBody AllergyDto dto) {
        webClientMH.put()
                .uri("/allergies/{id}", id)
                .bodyValue(dto)
                .retrieve()
                .toBodilessEntity()
                .block();

        return ResponseEntity.ok().build();
    }
    @PutMapping("/prescription/{id}")
    public ResponseEntity<Void> updatePrescription(@PathVariable Long id, @RequestBody PrescriptionDto dto) {
        webClientMH.put()
                .uri("/prescription/{id}", id)
                .bodyValue(dto)
                .retrieve()
                .toBodilessEntity()
                .block();

        return ResponseEntity.ok().build();
    }

    @PutMapping("/vaccine/{id}")
    public ResponseEntity<Void> updateVaccine(@PathVariable Long id, @RequestBody VaccineDto dto) {
        webClientMH.put()
                .uri("/vaccine/{id}", id)
                .bodyValue(dto)
                .retrieve()
                .toBodilessEntity()
                .block();

        return ResponseEntity.ok().build();
    }


    @PutMapping("/weightheight/{id}")
    public ResponseEntity<Void> updateWeightHeight(@PathVariable Long id, @RequestBody WeightHeightDto dto) {
        webClientMH.put()
                .uri("/weightheight/{id}", id)
                .bodyValue(dto)
                .retrieve()
                .toBodilessEntity()
                .block();

        return ResponseEntity.ok().build();
    }
    @GetMapping("/fitness/exercises")
    public ResponseEntity<List<Exercise>> getExercises() {
        List<Exercise> exercises = webClientFApi.get()
                .uri("/exercises")
                .retrieve()
                .bodyToFlux(Exercise.class)
                .collectList()
                .block();
        return ResponseEntity.ok(exercises);
    }
    @GetMapping("/fitness/muscles")
    public ResponseEntity<List<Muscle>> getAllMuscles() {
        List<Muscle> muscles = webClientFApi.get()
                .uri("/muscles")
                .retrieve()
                .bodyToFlux(Muscle.class)
                .collectList()
                .block();
        return ResponseEntity.ok(muscles);
    }

    @GetMapping("/fitness/muscles/{muscle}/exercises")
    public ResponseEntity<List<Exercise>> getExercisesByMuscle(@PathVariable String muscle) {
        List<Exercise> exercises = webClientFApi.get()
                .uri("/muscles/{muscle}/exercises", muscle)
                .retrieve()
                .bodyToFlux(Exercise.class)
                .collectList()
                .block();
        return ResponseEntity.ok(exercises);
    }

    @GetMapping("/fitness/bodyparts/{bodyPart}/exercises")
    public ResponseEntity<List<Exercise>> getExercisesByBodyPart(@PathVariable String bodyPart) {
        List<Exercise> exercises = webClientFApi.get()
                .uri("/bodyparts/{bodyPart}/exercises", bodyPart)
                .retrieve()
                .bodyToFlux(Exercise.class)
                .collectList()
                .block();
        return ResponseEntity.ok(exercises);
    }

    @GetMapping("/fitness/equipments/{equipment}/exercises")
    public ResponseEntity<List<Exercise>> getExercisesByEquipment(@PathVariable String equipment) {
        List<Exercise> exercises = webClientFApi.get()
                .uri("/equipments/{equipment}/exercises", equipment)
                .retrieve()
                .bodyToFlux(Exercise.class)
                .collectList()
                .block();
        return ResponseEntity.ok(exercises);
    }

    @GetMapping("/fitness/equipments")
    public ResponseEntity<List<Equipments>> getAllEquipments() {
        List<Equipments> equipments = webClientFApi.get()
                .uri("/equipments")
                .retrieve()
                .bodyToFlux(Equipments.class)
                .collectList()
                .block();
        return ResponseEntity.ok(equipments);
    }


}