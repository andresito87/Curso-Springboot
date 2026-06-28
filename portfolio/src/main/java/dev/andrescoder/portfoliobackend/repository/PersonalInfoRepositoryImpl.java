package dev.andrescoder.portfoliobackend.repository;

import dev.andrescoder.portfoliobackend.model.PersonalInfo;
import dev.andrescoder.portfoliobackend.repository.interfaces.IPersonalInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PersonalInfoRepositoryImpl implements IPersonalInfoRepository {

    private final JdbcTemplate jdbcTemplate; // Inyección de dependencia de JdbcTemplate para interactuar con la base
    // de datos con el @RequiredArgsConstructor de Lombok
    private final RowMapper<PersonalInfo> personalInfoRowMapper = (resultSet, numRow) -> {
        PersonalInfo personalInfo = new PersonalInfo();
        personalInfo.setId(resultSet.getLong("id"));
        personalInfo.setFirstName(resultSet.getString("first_name"));
        personalInfo.setLastName(resultSet.getString("last_name"));
        personalInfo.setTitle(resultSet.getString("title"));
        personalInfo.setEmail(resultSet.getString("email"));
        personalInfo.setPhone(resultSet.getString("phone"));
        personalInfo.setProfileDescription(resultSet.getString("profile_description"));
        personalInfo.setProfileImageUrl(resultSet.getString("profile_image_url"));
        personalInfo.setYearsOfExperience(resultSet.getObject("years_of_experience", Integer.class));
        personalInfo.setLinkedinUrl(resultSet.getString("linkedin_url"));
        personalInfo.setGithubUrl(resultSet.getString("github_url"));

        return personalInfo;
    };

    @Override
    public PersonalInfo save(PersonalInfo personalInfo) {
        if (personalInfo.getId() == null) {
            String sql = "INSERT INTO personal_info (first_name, last_name, title, email, phone, profile_description,"
                    + " profile_image_url, years_of_experience, linkedin_url, github_url) VALUES (?, ?, ?, ?, ?, ?, " + "?, ?, ?, ?)";

            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(
                    connection -> {
                        PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
                        ps.setString(1, personalInfo.getFirstName());
                        ps.setString(2, personalInfo.getLastName());
                        ps.setString(3, personalInfo.getTitle());
                        ps.setString(4, personalInfo.getEmail());
                        ps.setString(5, personalInfo.getPhone());
                        ps.setString(6, personalInfo.getProfileDescription());
                        ps.setString(7, personalInfo.getProfileImageUrl());
                        // Para tipos de datos que pueden ser nulos, como years_of_experience, es recomendable usar
                        // setObject en lugar de setInt para manejar correctamente los valores nulos.
                        if (personalInfo.getYearsOfExperience() != null) {
                            ps.setInt(8, personalInfo.getYearsOfExperience());
                        } else {
                            ps.setNull(8, java.sql.Types.INTEGER);
                        }
                        ps.setString(9, personalInfo.getLinkedinUrl());
                        ps.setString(10, personalInfo.getGithubUrl());

                        return ps;
                    }, keyHolder
            );

            // Establecer el ID generado en el objeto personalInfo, si viene null detenemos la aplicación
            personalInfo.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
        } else {
            String sql =
                    "UPDATE personal_info SET first_name = ?, last_name = ?, title = ?, email = ?, phone = ?, " +
                            "profile_description = ?, profile_image_url = ?, years_of_experience = ?, linkedin_url = "
                            + "?, github_url = ? WHERE id = ?";
            jdbcTemplate.update(
                    sql,
                    personalInfo.getFirstName(),
                    personalInfo.getLastName(),
                    personalInfo.getTitle(),
                    personalInfo.getEmail(),
                    personalInfo.getPhone(),
                    personalInfo.getProfileDescription(),
                    personalInfo.getProfileImageUrl(),
                    personalInfo.getYearsOfExperience(),
                    personalInfo.getLinkedinUrl(),
                    personalInfo.getGithubUrl(),
                    personalInfo.getId()
            );
        }

        return personalInfo;
    }

    @Override
    public Optional<PersonalInfo> findById(Long id) {
        String sql = "SELECT * FROM personal_info WHERE id = ?";
        try {
            // ofNullable detiene la consulta si queryForObject ha devuelto una excepción por recuperar más de un
            // resultado. Los ids son únicos en la DB
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, personalInfoRowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }

    }

    @Override
    public List<PersonalInfo> findAll() {
        String sql = "SELECT * FROM personal_info";
        return jdbcTemplate.query(sql, personalInfoRowMapper);
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM personal_info WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
