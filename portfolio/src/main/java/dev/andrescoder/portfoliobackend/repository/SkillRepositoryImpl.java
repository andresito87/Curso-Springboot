package dev.andrescoder.portfoliobackend.repository;

import dev.andrescoder.portfoliobackend.model.Skill;
import dev.andrescoder.portfoliobackend.repository.interfaces.ISkillRepository;
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
public class SkillRepositoryImpl implements ISkillRepository {

    private final JdbcTemplate jdbcTemplate; // Inyección de dependencia de JdbcTemplate para interactuar con la base de datos con el @RequiredArgsConstructor de Lombok
    private final RowMapper<Skill> skillRowMapper = (resultSet, numRow) -> {
        Skill skill = new Skill();
        skill.setId(resultSet.getLong("id"));
        skill.setName(resultSet.getString("name"));
        skill.setIconClass(resultSet.getString("icon_class"));
        skill.setLevelPercentage(resultSet.getInt("level_percentage"));
        skill.setPersonalInfoId(resultSet.getLong("personal_info_id"));
        return skill;
    };

    @Override
    public Skill save(Skill skill) {
        if (skill.getId() == null) {
            String sql = "INSERT INTO skills (name, icon_class, level_percentage, personal_info_id) VALUES (?, ?, ?, ?)";
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
                ps.setString(1, skill.getName());
                ps.setString(2, skill.getIconClass());
                ps.setInt(3, skill.getLevelPercentage());
                ps.setLong(4, skill.getPersonalInfoId());
                return ps;
            }, keyHolder);

            // Establecer el ID generado en el objeto skill, si viene null detenemos la aplicación
            skill.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
        } else {
            String sql = "UPDATE skills SET name = ?, icon_class = ?, level_percentage = ?, personal_info_id = ? WHERE id = ?";
            jdbcTemplate.update(sql, skill.getName(), skill.getIconClass(), skill.getLevelPercentage(), skill.getPersonalInfoId(), skill.getId());
        }

        return skill;
    }

    @Override
    public Optional<Skill> findById(Long id) {
        String sql = "SELECT * FROM skills WHERE id = ?";
        try {
            // ofNullable detiene la consulta si queryForObject ha devuelto una excepción por recuperar más de un resultado. Los ids son únicos en la DB
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, skillRowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }

    }

    @Override
    public List<Skill> findAll() {
        String sql = "SELECT * FROM skills";
        return jdbcTemplate.query(sql, skillRowMapper);
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM skills WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Skill> findSkillsByPersonalInfoId(Long personalInfoId) {
        String sql = "SELECT * FROM skills WHERE personal_info_id = ?";
        return jdbcTemplate.query(sql, skillRowMapper, personalInfoId);
    }
}