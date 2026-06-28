package dev.andrescoder.portfoliobackend.repository;

import dev.andrescoder.portfoliobackend.model.User;
import dev.andrescoder.portfoliobackend.repository.interfaces.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements IUserRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Optional<User> findByUsername(String username) {

        String sql = "SELECT * FROM users WHERE username = ?";

        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, this::mapRowToUser, username));
        } catch (Exception e) {

            return Optional.empty();
        }
    }

    // En este caso utilizamos una función privada mapper en lugar de un atributo inicializado con una expresión lambda
    private User mapRowToUser(ResultSet rs, int numRow) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setEnabled(rs.getBoolean("enabled"));
        return user;
    }
}
