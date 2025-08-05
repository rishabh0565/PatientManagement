package com.pm.patient_service.controller;

import java.util.List;
import java.util.UUID;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.groups.Default; // or javax.validation.groups.Default if using javax

import com.pm.patient_service.dto.PatientRequestDTO;
import com.pm.patient_service.dto.PatientResponseDTO;
import com.pm.patient_service.service.PatientService;
import com.pm.patient_service.validationgroup.PatientValidationGroup;




@RestController
@RequestMapping("/patients")
public class PatientController {
	@Autowired
	private PatientService patientService;

	@GetMapping("/all")
	public ResponseEntity<List<PatientResponseDTO>> getAllPatients() {

		List<PatientResponseDTO> allPatients = patientService.getAllPatients();
		return ResponseEntity.ok().body(allPatients); 
	}
	@PostMapping()
	private ResponseEntity<PatientResponseDTO> createPatient(@Validated({Default.class,PatientValidationGroup.class}) @RequestBody PatientRequestDTO dtoRequest) {
		PatientResponseDTO patient = patientService.createPatient(dtoRequest); 
		return ResponseEntity.ok().body(patient); 

	}
	
	@PutMapping("/{id}")
	private ResponseEntity<PatientResponseDTO> updatePatient(@PathVariable UUID id, @Validated(Default.class) @RequestBody PatientRequestDTO dto ){
		PatientResponseDTO updatePatient = patientService.updatePatient(id, dto);
		return ResponseEntity.ok().body(updatePatient); 
	}
	
	@DeleteMapping("/{id}")
	private ResponseEntity<String> deletePatient(@PathVariable UUID id){
		String deletePatient = patientService.deletePatient(id);
		return ResponseEntity.ok().body(deletePatient); 
	}
}
