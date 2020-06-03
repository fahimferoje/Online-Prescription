package com.cmed.prescription.restapi.controller;

import com.cmed.prescription.web.model.Patient;
import com.cmed.prescription.web.model.Prescription;
import com.cmed.prescription.web.model.User;
import com.cmed.prescription.web.service.JwtUserDetailsService;
import com.cmed.prescription.web.service.PatientDetailsService;
import com.cmed.prescription.web.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/prescription")
@Validated
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
}
