package dev.andrescoder.portfoliobackend.service;

import dev.andrescoder.portfoliobackend.exception.ValidationException;
import dev.andrescoder.portfoliobackend.model.Experience;
import dev.andrescoder.portfoliobackend.repository.IExperienceRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ExperienceServiceTest {

    @Autowired
    private IExperienceService experienceService;
    @Autowired
    private IExperienceRepository experienceRepository;

    @Test
    void testSaveValidExperience() {
        Experience validExperience = new Experience(null, "Software Engineer", "Company X", LocalDate.of(2020, 1, 1), null, "Descripción", 1L);
        Experience savedExperience = experienceService.save(validExperience);

        assertNotNull(savedExperience.getId(), "Saved experience must have an id");
        assertNotNull(experienceRepository.findById(savedExperience.getId()).orElse(null), "Saved experience must exist in the database");
    }

    @Test
    void testSaveInvalidCompanyName() {
        Experience invalidExperience = new Experience(null, "Software Engineer", "", LocalDate.of(2020, 1, 1), null, "Descripción", 1L);

        assertThrows(ValidationException.class, () -> experienceService.save(invalidExperience), "A ValidationException must be thrown when the company name is empty");
    }
}
