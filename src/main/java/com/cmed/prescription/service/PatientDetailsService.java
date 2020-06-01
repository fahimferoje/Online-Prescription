package com.cmed.prescription.service;

import com.cmed.prescription.model.Patient;
import com.cmed.prescription.repo.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientDetailsService {

    @Autowired
    PatientRepository patientRepository;

    public List<Patient> findAllPatients() {
        return patientRepository.findAll();
    }

}
