package dev.andrescoder.portfoliobackend.repository;

import dev.andrescoder.portfoliobackend.model.Education;
import dev.andrescoder.portfoliobackend.repository.interfaces.IEducationRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class EducationRepositoryImpl implements IEducationRepository {

    private final JdbcTemplate jdbcTemplate; // Inyectado a través de @RequiredArgsConstructor de Lombok para interactuar con la base de datos
    private final RowMapper<Education> educationRowMapper = (resultSet, numRow) -> {
        Education education = new Education();
        education.setId(resultSet.getLong("id"));
        education.setDegree(resultSet.getString("degree"));
        education.setInstitution(resultSet.getString("institution"));
        education.setStartDate(resultSet.getDate("start_date").toLocalDate());
        education.setEndDate(resultSet.getDate("end_date").toLocalDate());
        education.setDescription(resultSet.getString("description"));
        education.setPersonalInfoId(resultSet.getLong("personal_info_id"));

        return education;
    };

    @Override
    public Education save(Education education) {
        if (education.getId() == null) {

            String sql = "INSERT INTO educations (degree, institution, start_date, end_date, description, personal_info_id) VALUES (?, ?, ?, ?, ?, ?)";
            KeyHolder keyHolder = new GeneratedKeyHolder();

            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
                ps.setString(1, education.getDegree());
                ps.setString(2, education.getInstitution());
                ps.setObject(3, education.getStartDate());
                ps.setObject(4, education.getEndDate());
                ps.setString(5, education.getDescription());
                ps.setLong(6, education.getPersonalInfoId());
                return ps;
            }, keyHolder);

            education.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());

        } else {
            String sql = "UPDATE educations SET degree = ?, institution = ?, start_date = ?, end_date = ?, description = ?, personal_info_id = ? WHERE id = ?";
            jdbcTemplate.update(sql, education.getDegree(), education.getInstitution(), education.getStartDate(), education.getEndDate(), education.getDescription(), education.getPersonalInfoId(), education.getId());
        }
        return education;
    }

    @Override
    public Optional<Education> findById(Long id) {
        String sql = "SELECT * FROM educations WHERE id = ?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, educationRowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Education> findAll() {
        String sql = "SELECT * FROM educations";
        return jdbcTemplate.query(sql, educationRowMapper);
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM educations WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Education> findEducationsByPersonalInfoId(Long personalInfoId) {
        String sql = "SELECT * FROM educations WHERE personal_info_id = ?";
        return jdbcTemplate.query(sql, educationRowMapper, personalInfoId);
    }
}
