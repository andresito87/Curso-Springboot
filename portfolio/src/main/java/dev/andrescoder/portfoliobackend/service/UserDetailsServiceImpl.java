package dev.andrescoder.portfoliobackend.service;

import dev.andrescoder.portfoliobackend.model.User;
import dev.andrescoder.portfoliobackend.repository.interfaces.IUserRepository;
import dev.andrescoder.portfoliobackend.service.interfaces.IUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements IUserDetailsService {

    private final IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found" + username));

        // Crea una cuenta en el sistema de autenticación de Springboot, usa la clase User de Springboot
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.isEnabled(),
                true,
                true,
                true,
                Collections.emptyList()
        );
    }
}
