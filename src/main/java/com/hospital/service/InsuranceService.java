package com.hospital.service;

import com.hospital.entity.Insurance;
import com.hospital.entity.Patient;
import com.hospital.repository.InsuranceRepository;
import com.hospital.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class InsuranceService {

    private final InsuranceRepository insuranceRepository;
    private final PatientRepository patientRepository;

    public InsuranceService(InsuranceRepository insuranceRepository, PatientRepository patientRepository) {
        this.insuranceRepository = insuranceRepository;
        this.patientRepository = patientRepository;
    }

    @Transactional
    public Patient assignInsuranceToPatient(Insurance insurance, Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found with id: " + patientId));

        patient.setInsurance(insurance);
        insurance.setPatient(patient); // bidirectional consistency maintenance

        return patient;
    }
    @Transactional
    public Patient disaccociateInsuranceFromPatient(Long patientId) {

        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found with id: "+patientId));

                patient.setInsurance(null);
                return patient;
    }
}

