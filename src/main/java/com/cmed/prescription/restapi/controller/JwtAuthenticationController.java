package com.cmed.prescription.restapi.controller;

import com.cmed.prescription.config.CustomAuthenticationManager;
import com.cmed.prescription.web.model.JwtRequest;
import com.cmed.prescription.web.model.JwtResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

    private final CustomAuthenticationManager authenticationManager;

    public JwtAuthenticationController(CustomAuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @RequestMapping(value = "/api/v1/token", method = RequestMethod.POST)
    public ResponseEntity getToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        UsernamePasswordAuthenticationToken upAuthToken = (UsernamePasswordAuthenticationToken) authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authenticationRequest.getEmail()
                , authenticationRequest.getPassword()));

        return ResponseEntity.ok(new JwtResponse(upAuthToken.getCredentials().toString()));
    }
}