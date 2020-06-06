package com.cmed.prescription.config;

import com.cmed.prescription.restapi.config.JwtTokenUtil;
import com.cmed.prescription.web.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationManager implements AuthenticationProvider {

    @Autowired
    private JwtUserDetailsService userDetailsService;

    //@Autowired
    //private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        UserDetails userDetails = userDetailsService
                .loadUserByUsername((String) authentication.getPrincipal());

        BCryptPasswordEncoder b = new BCryptPasswordEncoder();

        boolean matched = b.matches((String) authentication.getCredentials()
                , userDetails.getPassword());

        if (!matched) {
            throw new PasswordNotMatchException("Credentials does not match");
        }

        String token = jwtTokenUtil.generateToken(userDetails);

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken((String) authentication.getPrincipal(), token
                        , userDetails.getAuthorities());

        SecurityContextHolder.getContext()
                .setAuthentication(usernamePasswordAuthenticationToken);

        return usernamePasswordAuthenticationToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
