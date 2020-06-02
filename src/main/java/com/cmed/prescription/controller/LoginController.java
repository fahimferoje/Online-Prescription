package com.cmed.prescription.controller;

import com.cmed.prescription.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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

    @GetMapping("/edit/{id}")
    public ModelAndView editPrescription(@PathVariable("id") Long presId) {

        ModelAndView modelAndView = new ModelAndView();

        return modelAndView;
    }
}
