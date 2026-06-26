package dev.andrescoder.portfoliobackend.repository;

import dev.andrescoder.portfoliobackend.model.Project;
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
public class ProjectRepositoryImpl implements IProjectRepository {

    private final JdbcTemplate jdbcTemplate;

    // Mapper para transformar cada fila de la tabla de la BD a un objeto project en Java
    private final RowMapper<Project> projectRowMapper = (rs, rowNum) -> {
        Project project = new Project();
        project.setId(rs.getLong("id"));
        project.setTitle(rs.getString("title"));
        project.setDescription(rs.getString("description"));
        project.setImageUrl(rs.getString("image_url"));
        project.setProjectUrl(rs.getString("project_url"));
        project.setPersonalInfoId(rs.getLong("personal_info_id"));
        return project;
    };

    @Override
    public List<Project> findAll() {
        String sql = "SELECT * FROM projects";
        return jdbcTemplate.query(sql, projectRowMapper);
    }

    @Override
    public Optional<Project> findById(Long id) {
        String sql = "SELECT * FROM projects WHERE id = ?";
        try {
            // ofNullable detiene la consulta si queryForObject ha devuelto una excepción por recuperar más de un
            // resultado. Los ids son únicos en la DB por tanto el elemento retornado tiene que ser único
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, projectRowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Project save(Project project) {

        if (project.getId() == null) {
            String sql = "INSERT INTO projects (title, description, image_url, project_url, personal_info_id) VALUES "
                    + "(?, ?, ?, ?, ?)";

            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(
                    connection -> {
                        PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
                        ps.setString(1, project.getTitle());
                        ps.setString(2, project.getDescription());
                        ps.setString(3, project.getImageUrl());
                        ps.setString(4, project.getProjectUrl());
                        ps.setLong(5, project.getPersonalInfoId());

                        return ps;
                    }, keyHolder
            );

            // Establecer el ID generado en el objeto project, si viene null detenemos la aplicación
            project.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());

        } else {
            String sql = "UPDATE projects SET title = ?, description = ?, image_url = ?, project_url = ?, " +
                    "personal_info_id = ? WHERE id = ?";
            jdbcTemplate.update(
                    sql,
                    project.getTitle(),
                    project.getDescription(),
                    project.getImageUrl(),
                    project.getProjectUrl(),
                    project.getPersonalInfoId(),
                    project.getId()
            );
        }

        return project;

    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM projects WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
