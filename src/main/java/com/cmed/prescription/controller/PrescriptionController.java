package com.cmed.prescription.controller;

import com.cmed.prescription.model.Patient;
import com.cmed.prescription.model.Prescription;
import com.cmed.prescription.model.PrescriptionReqBody;
import com.cmed.prescription.model.User;
import com.cmed.prescription.repo.PrescriptionRepository;
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
    public ResponseEntity<?> savePrescription(@RequestBody PrescriptionReqBody prescriptionReqBody) {

        Patient patient = patientDetailsService.findPatientById(prescriptionReqBody.getPatientId());

        User user = userDetailsService.findUserById(prescriptionReqBody.getUserId());

        if(patient == null || user == null) {
            return ResponseEntity.notFound().build();
        }

        Prescription newPrescription = new Prescription();
        newPrescription.setCreatedAt(prescriptionReqBody.getCreatedAt());
        newPrescription.setDiagnosis(prescriptionReqBody.getDiagnosis());
        newPrescription.setMedicines(prescriptionReqBody.getMedicines());
        newPrescription.setNextVisitDate(prescriptionReqBody.getNextVisitDate());
        newPrescription.setPatient(patient);
        newPrescription.setUser(user);

        prescriptionService.save(newPrescription);

        return ResponseEntity.ok(prescriptionService.getAllPrescriptions());
    }



}
