package com.cmed.prescription.controller;

import com.cmed.prescription.model.Patient;
import com.cmed.prescription.model.Prescription;
import com.cmed.prescription.service.JwtUserDetailsService;
import com.cmed.prescription.service.PatientDetailsService;
import com.cmed.prescription.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.swing.text.html.Option;
import javax.validation.Valid;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
public class LoginController {

    @Autowired
    PrescriptionService prescriptionService;

    @Autowired
    PatientDetailsService patientDetailsService;

    @Autowired
    JwtUserDetailsService userDetailsService;

    @RequestMapping(value="/login", method = RequestMethod.GET)
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @GetMapping("/prescription")
    public ModelAndView viewPrescription() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("prescriptions", prescriptionService.getAllPrescriptions());
        modelAndView.setViewName("prescription");

        return modelAndView;
    }

    @GetMapping("/findOne")
    @ResponseBody
    public Prescription findPrescriptionById(Long id) {

        Optional<Prescription> pres = prescriptionService.findById(id);

        return pres.isPresent() ? pres.get() : null;
    }

    @GetMapping("/deleteById")
    public String deletePrescriptionById(Long id) {
        prescriptionService.deleteById(id);
        return "redirect:/prescription";
    }

    @RequestMapping(value = "/saveOrEdit", method = RequestMethod.POST)
    public String savePrescription(Prescription prescription) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        prescription.setUser(userDetailsService.findUserByUserName(auth.getName()));

        DateFormat format = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);

        try {

            Date date = format.parse(prescription.getNextVisitDateString());
            prescription.setNextVisitDate(date);
        }
        catch (Exception e) {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DAY_OF_MONTH, 7);
            prescription.setNextVisitDate(cal.getTime());
        }

        prescription.setCreatedAt(new Date());
        prescription.setPatient(patientDetailsService.findPatientById(prescription.getPatientId()));

        prescriptionService.save(prescription);

        return "redirect:/prescription";
    }

    @GetMapping("/patients")
    @ResponseBody
    public List<Patient> getPatients() {
        return patientDetailsService.findAllPatients();
    }
}
