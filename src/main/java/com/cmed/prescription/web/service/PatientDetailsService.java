package com.cmed.prescription.web.service;

import com.cmed.prescription.web.model.Patient;
import com.cmed.prescription.web.repo.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientDetailsService {

    @Autowired
    PatientRepository patientRepository;

    public List<Patient> findAllPatients() {
        return patientRepository.findAll();
    }

    public Patient findPatientById(Long patientId) {

        Optional<Patient> patientOp = patientRepository.findById(patientId);
        return patientOp.isPresent() ? patientOp.get() : null;

    }

}
