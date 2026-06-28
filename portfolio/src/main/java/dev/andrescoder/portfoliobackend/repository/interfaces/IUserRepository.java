package dev.andrescoder.portfoliobackend.repository.interfaces;

import dev.andrescoder.portfoliobackend.model.User;

import java.util.Optional;

public interface IUserRepository {
    Optional<User> findByUsername(String username); // En nuestra BD el nombre de usuario es único
}
