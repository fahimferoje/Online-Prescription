package com.cmed.prescription.restapi.controller;

import com.cmed.prescription.config.CustomAuthenticationManager;
import com.cmed.prescription.web.model.JwtRequest;
import com.cmed.prescription.web.model.JwtResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

    private final CustomAuthenticationManager authenticationManager;

    public JwtAuthenticationController(CustomAuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @RequestMapping(value = "/api/v1/token", method = RequestMethod.POST)
    public ResponseEntity getToken(@RequestBody JwtRequest authenticationRequest) {

        try {

            UsernamePasswordAuthenticationToken upAuthToken = (UsernamePasswordAuthenticationToken) authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getEmail()
                    , authenticationRequest.getPassword()));

            return ResponseEntity.ok(new JwtResponse(upAuthToken.getCredentials().toString()));

        }
        catch (AuthenticationException ex) {

            Map<String, Object> body = new LinkedHashMap<>();
            body.put("timestamp", new Date());
            body.put("status", HttpStatus.UNAUTHORIZED.value());
            body.put("userMsg", "email or password is not correct");
            body.put("devMsg", ex.getMessage());

            return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
        }
    }
}