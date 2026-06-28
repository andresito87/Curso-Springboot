package dev.andrescoder.portfoliobackend.utils;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;

public class PasswordGenerator {

    public static void main(String[] args) {
        String encodedPassword = PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("1234");
        System.out.println("Generated Password: " + encodedPassword);
    }

}
