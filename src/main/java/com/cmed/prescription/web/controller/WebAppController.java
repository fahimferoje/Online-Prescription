package com.cmed.prescription.web.controller;

import com.cmed.prescription.web.model.DailyPrescriptionCount;
import com.cmed.prescription.web.model.DateRange;
import com.cmed.prescription.web.model.Patient;
import com.cmed.prescription.web.model.Prescription;
import com.cmed.prescription.web.service.JwtUserDetailsService;
import com.cmed.prescription.web.service.PatientDetailsService;
import com.cmed.prescription.web.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class WebAppController {

    @Autowired
    PrescriptionService prescriptionService;

    @Autowired
    PatientDetailsService patientDetailsService;

    @Autowired
    JwtUserDetailsService userDetailsService;

    @GetMapping("/")
    public String home() {
        return "redirect:/login";
    }

    @RequestMapping(value="/login", method = RequestMethod.GET)
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login-view-2");
        return modelAndView;
    }

    @GetMapping("/prescription")
    public ModelAndView viewPrescription() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("prescriptions", prescriptionService.getAllPrescriptions());
        DateRange dateRange = new DateRange();
        dateRange.setDateFrom(new Date());
        dateRange.setDateTo(new Date());
        modelAndView.addObject("dateRange", dateRange);
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

    @PostMapping("/search")
    public ModelAndView searchByDate(DateRange dateRange) {

        List<Prescription> prescriptionList = prescriptionService
                .findPrescriptionsByDate(dateRange.getDateFrom(), dateRange.getDateTo());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("prescriptions", prescriptionList);
        modelAndView.addObject("dateRange", dateRange);
        modelAndView.setViewName("prescription");

        return modelAndView;
    }

    @GetMapping("/daily_prescription_count")
    @ResponseBody
    public List<DailyPrescriptionCount> getDailyPrescriptionsCount() {
        return prescriptionService.findPrescriptionsDailyCount();
    }
}
