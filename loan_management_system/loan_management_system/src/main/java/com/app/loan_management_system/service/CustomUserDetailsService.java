package com.app.loan_management_system.service;

import java.util.Collections;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    // Replace with actual user repo or static data
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Dummy user
        if ("admin".equals(username)) {
            return new User("admin", new BCryptPasswordEncoder().encode("password"),
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN")));
        }
        throw new UsernameNotFoundException("User not found");
    }
}
