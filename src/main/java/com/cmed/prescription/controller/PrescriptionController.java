package com.cmed.prescription.controller;

import com.cmed.prescription.model.Patient;
import com.cmed.prescription.model.Prescription;
import com.cmed.prescription.model.User;
import com.cmed.prescription.service.JwtUserDetailsService;
import com.cmed.prescription.service.PatientDetailsService;
import com.cmed.prescription.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/prescription")
public class PrescriptionController {

    @Autowired
    private PrescriptionService prescriptionService;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private PatientDetailsService patientDetailsService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<?> getAllPrescriptions() {
        return ResponseEntity.ok(prescriptionService.getAllPrescriptions());
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<?> savePrescription(@RequestBody Prescription prescription) {

        Patient patient = patientDetailsService
                .findPatientById(prescription.getPatientId());

        User user = userDetailsService.findUserById(prescription.getUserId());

        if(patient == null || user == null) {
            return ResponseEntity.notFound().build();
        }

        prescription.setUser(user);
        prescription.setPatient(patient);

        prescriptionService.save(prescription);

        return ResponseEntity.ok(prescriptionService.getAllPrescriptions());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> editPrescription(@PathVariable("id") Long id, @RequestBody Prescription prescription) {

        Optional<Prescription> prescriptionOp = prescriptionService.findById(id);

        if(!prescriptionOp.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Prescription prescriptionEntity = prescriptionOp.get();

        Patient patient = prescription.getPatientId() != null ?
                patientDetailsService.findPatientById(prescription.getPatientId()) : null;

        User user = prescription.getUserId() != null ?
                userDetailsService.findUserById(prescription.getUserId()) : null;

        if(patient == null || user == null) {
            return ResponseEntity.notFound().build();
        }

        prescriptionEntity.setUser(user);
        prescriptionEntity.setPatient(patient);
        prescriptionEntity.setMedicines(prescription.getMedicines());
        prescriptionEntity.setNextVisitDate(prescription.getNextVisitDate());
        prescriptionEntity.setDiagnosis(prescription.getDiagnosis());
        prescriptionEntity.setCreatedAt(prescription.getCreatedAt());

        prescriptionService.save(prescriptionEntity);

        return ResponseEntity.ok(prescriptionService.getAllPrescriptions());
    }



}
