package com.cmed.prescription.service;

import com.cmed.prescription.model.Patient;
import com.cmed.prescription.model.Prescription;
import com.cmed.prescription.repo.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class PrescriptionService {

    @Autowired
    PrescriptionRepository prescriptionRepository;

    public List<Prescription> getAllPrescriptions() {
        return prescriptionRepository.findAll();
    }

    public void save(Prescription newPrescription) {
        prescriptionRepository.save(newPrescription);
    }

    public Optional<Prescription> findById(Long id) {
        return prescriptionRepository.findById(id);
    }

    public void deleteById(Long id) {
        prescriptionRepository.deleteById(id);
    }
}
