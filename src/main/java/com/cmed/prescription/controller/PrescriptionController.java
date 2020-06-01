package com.cmed.prescription.controller;

import com.cmed.prescription.model.Patient;
import com.cmed.prescription.model.Prescription;
import com.cmed.prescription.model.User;
import com.cmed.prescription.service.JwtUserDetailsService;
import com.cmed.prescription.service.PatientDetailsService;
import com.cmed.prescription.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

        Patient patient = patientDetailsService.findPatientById(prescription.getPatientId());

        User user = userDetailsService.findUserById(prescription.getUserId());

        if(patient == null || user == null) {
            return ResponseEntity.notFound().build();
        }

        prescription.setUser(user);
        prescription.setPatient(patient);

        prescriptionService.save(prescription);

        return ResponseEntity.ok(prescriptionService.getAllPrescriptions());
    }



}
