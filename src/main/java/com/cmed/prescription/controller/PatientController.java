package com.cmed.prescription.controller;

import com.cmed.prescription.model.Patient;
import com.cmed.prescription.service.PatientDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/patients")
public class PatientController {

    @Autowired
    PatientDetailsService patientDetailsService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<?> getAllPatients() {
        return ResponseEntity.ok(patientDetailsService.findAllPatients());
    }
}
