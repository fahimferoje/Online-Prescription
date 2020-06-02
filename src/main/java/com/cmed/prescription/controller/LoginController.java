package com.cmed.prescription.controller;

import com.cmed.prescription.model.Prescription;
import com.cmed.prescription.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.swing.text.html.Option;
import java.util.Optional;

@Controller
public class LoginController {

    @Autowired
    PrescriptionService prescriptionService;

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

    @DeleteMapping("/deleteById/{id}")
    public String deletePrescriptionById(@PathVariable  Long id) {
        prescriptionService.deleteById(id);
        return "redirect:/prescription";
    }
}
