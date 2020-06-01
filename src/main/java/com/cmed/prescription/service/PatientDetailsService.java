package com.cmed.prescription.service;

import com.cmed.prescription.model.Patient;
import com.cmed.prescription.repo.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
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
