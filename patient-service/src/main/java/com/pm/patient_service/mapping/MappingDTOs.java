package com.pm.patient_service.mapping;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pm.patient_service.dto.PatientRequestDTO;
import com.pm.patient_service.dto.PatientResponseDTO;
import com.pm.patient_service.model.Patient;

@Component 
public class MappingDTOs {
  
    
    public PatientResponseDTO convertIntoDto(Patient patient) {
    	 PatientResponseDTO patientDTO  = new PatientResponseDTO(); 
    	patientDTO.setId(patient.getId().toString());
    	patientDTO.setName(patient.getName());
    	patientDTO.setEmail(patient.getEmail());
    	patientDTO.setAddress(patient.getAddress());
    	patientDTO.setDateOfBirth(patient.getDateOfBirth().toString());
    	patientDTO.setRegistrationDate(patient.getRegisterationDate().toString());
       return patientDTO; 

	}
    
    public Patient converttomode(PatientRequestDTO dto) {
    	Patient patient = new Patient(); 
		 patient.setName(dto.getName()); 
		 patient.setEmail(dto.getEmail());
		 patient.setAddress(dto.getAddress());
		 patient.setDateOfBirth(LocalDate.parse(dto.getDateOfBirth()));
		 patient.setRegisterationDate(LocalDate.parse(dto.getRegistrationDate()));
		 return patient; 
 
	}
}
