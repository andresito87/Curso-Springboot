package dev.andrescoder.portfoliobackend.repository;

import dev.andrescoder.portfoliobackend.model.Experience;
import dev.andrescoder.portfoliobackend.repository.interfaces.IExperienceRepository;
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
public class ExperienceRepositoryImpl implements IExperienceRepository {

    private final JdbcTemplate jdbcTemplate; // Inyectado a través de @RequiredArgsConstructor de Lombok para interactuar con la base de datos
    private final RowMapper<Experience> experienceRowMapper = (resultSet, numRow) -> {
        Experience experience = new Experience();
        experience.setId(resultSet.getLong("id"));
        experience.setJobTitle(resultSet.getString("job_title"));
        experience.setCompanyName(resultSet.getString("company_name"));
        experience.setStartDate(resultSet.getDate("start_date").toLocalDate());
        if (resultSet.getDate("end_date") != null) {
            experience.setEndDate(resultSet.getDate("end_date").toLocalDate());
        } else {
            experience.setEndDate(null);
        }
        experience.setDescription(resultSet.getString("description"));
        experience.setPersonalInfoId(resultSet.getLong("personal_info_id"));

        return experience;
    };

    @Override
    public Experience save(Experience experience) {
        if (experience.getId() == null) {

            String sql = "INSERT INTO experiences (job_title, company_name, start_date, end_date, description, personal_info_id) VALUES (?, ?, ?, ?, ?, ?)";
            KeyHolder keyHolder = new GeneratedKeyHolder();

            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
                ps.setString(1, experience.getJobTitle());
                ps.setString(2, experience.getCompanyName());
                ps.setObject(3, experience.getStartDate());
                ps.setObject(4, experience.getEndDate());
                ps.setString(5, experience.getDescription());
                ps.setLong(6, experience.getPersonalInfoId());
                return ps;
            }, keyHolder);

            experience.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());

        } else {
            String sql = "UPDATE experiences SET job_title = ?, company_name = ?, start_date = ?, end_date = ?, description = ?, personal_info_id = ? WHERE id = ?";
            jdbcTemplate.update(sql, experience.getJobTitle(), experience.getCompanyName(), experience.getStartDate(), experience.getEndDate(), experience.getDescription(), experience.getPersonalInfoId(), experience.getId());
        }
        return experience;
    }

    @Override
    public Optional<Experience> findById(Long id) {
        String sql = "SELECT * FROM experiences WHERE id = ?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, experienceRowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Experience> findAll() {
        String sql = "SELECT * FROM experiences";
        return jdbcTemplate.query(sql, experienceRowMapper);
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM experiences WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Experience> findExperiencesByPersonalInfoId(Long personalInfoId) {
        String sql = "SELECT * FROM experiences WHERE personal_info_id = ?";
        return jdbcTemplate.query(sql, experienceRowMapper, personalInfoId);
    }
}
