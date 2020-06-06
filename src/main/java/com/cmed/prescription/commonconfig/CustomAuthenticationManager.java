package com.cmed.prescription.commonconfig;

import com.cmed.prescription.restapi.config.JwtTokenUtil;
import com.cmed.prescription.web.service.JwtUserDetailsService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationManager implements AuthenticationProvider {

    private final JwtUserDetailsService userDetailsService;

    private final JwtTokenUtil jwtTokenUtil;

    public CustomAuthenticationManager(JwtUserDetailsService userDetailsService, JwtTokenUtil jwtTokenUtil) {
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        UserDetails userDetails = userDetailsService
                .loadUserByUsername((String) authentication.getPrincipal());

        BCryptPasswordEncoder b = new BCryptPasswordEncoder();

        boolean matched = b.matches((String) authentication.getCredentials()
                , userDetails.getPassword());

        if (!matched) {
            throw new PasswordNotMatchException("Incorrect Email or Password");
        }

        String token = jwtTokenUtil.generateToken(userDetails);

        return new UsernamePasswordAuthenticationToken((String) authentication.getPrincipal(), token
                , userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
