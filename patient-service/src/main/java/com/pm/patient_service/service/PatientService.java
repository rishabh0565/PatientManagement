package com.pm.patient_service.service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pm.patient_service.dto.PatientRequestDTO;
import com.pm.patient_service.dto.PatientResponseDTO;
import com.pm.patient_service.exception.EmailAlreadyExistException;
import com.pm.patient_service.exception.PatientNotFoundException;
import com.pm.patient_service.grpc.BillingServiceGrpcClient;
import com.pm.patient_service.kafka.KafkaProducer;
import com.pm.patient_service.mapping.MappingDTOs;
import com.pm.patient_service.model.Patient;
import com.pm.patient_service.repository.PatientRepository;

import billing.BillingResponse;

@Service
public class PatientService {
	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private MappingDTOs mapDtos;

	@Autowired
	private KafkaProducer kafkaProducer;

	@Autowired
	private BillingServiceGrpcClient billingServiceGrpcClient;

	public List<PatientResponseDTO> getAllPatients() {
		List<Patient> patients = patientRepository.findAll();
		List<PatientResponseDTO> list = patients.stream().map(patient -> mapDtos.convertIntoDto(patient)).toList();
		return list;
	}

	public PatientResponseDTO createPatient(PatientRequestDTO dto) {
		if (patientRepository.existsByEmail(dto.getEmail())) {
			throw new EmailAlreadyExistException("A Patient with this email " + dto.getEmail() + " Already exists");
		}
		Patient converttomode = mapDtos.converttomode(dto);
		Patient save = patientRepository.save(converttomode);
		PatientResponseDTO convertIntoDto = mapDtos.convertIntoDto(save);
		billingServiceGrpcClient.createBillingAccount(save.getId().toString(), save.getName(),
				save.getRegisterationDate().toString());
		kafkaProducer.sendEvent(save);
		return convertIntoDto;
	}

	public PatientResponseDTO updatePatient(UUID id, PatientRequestDTO dto) {
		Patient byId = patientRepository.findById(id).orElseThrow(
				() -> new PatientNotFoundException("This patient with  ID -> " + id + "  is not in our database"));
		if (!byId.getEmail().equals(dto.getEmail()) && patientRepository.existsByEmail(dto.getEmail())) {
			throw new EmailAlreadyExistException("A Patient with this email " + dto.getEmail() + " Already exists");
		}
		byId.setName(dto.getName());
		byId.setEmail(dto.getEmail());
		byId.setAddress(dto.getAddress());
		byId.setDateOfBirth(LocalDate.parse(dto.getDateOfBirth()));
		byId.setRegisterationDate(LocalDate.parse(dto.getRegistrationDate()));
		Patient save = patientRepository.save(byId);
		PatientResponseDTO convertIntoDto = mapDtos.convertIntoDto(save);
		return convertIntoDto;

	}

	public String deletePatient(UUID id) {
		if (!patientRepository.existsById(id)) {
			return "" + id + "-> does not exist";
		}
		BillingResponse deleteBillingAccount = billingServiceGrpcClient.deleteBillingAccount(id.toString());
		patientRepository.deleteById(id);
		return "deleted sucessfully and billing account details are " + deleteBillingAccount;

	}

}
