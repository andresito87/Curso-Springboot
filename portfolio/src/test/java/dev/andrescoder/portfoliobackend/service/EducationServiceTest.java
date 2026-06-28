package dev.andrescoder.portfoliobackend.service;

import dev.andrescoder.portfoliobackend.exception.ValidationException;
import dev.andrescoder.portfoliobackend.model.Education;
import dev.andrescoder.portfoliobackend.repository.interfaces.IEducationRepository;
import dev.andrescoder.portfoliobackend.service.interfaces.IEducationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
// Reinicia el estado de la BD antes de cada test
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class EducationServiceTest {

    @Autowired
    private IEducationService educationService;

    @Autowired
    private IEducationRepository educationRepository;

    @Test
    void testSaveValidEducation() {
        Education education = new Education(
                null,
                "University of Test",
                "Bachelor of Testing",
                LocalDate.of(2020, 1, 1),
                LocalDate.of(2024, 1, 1),
                "Allowed to test the testing of tests",
                1L
        );
        educationRepository.save(education);

        assertNotNull(education.getId(), "Saved education must have an ID");
        assertNotNull(
                educationRepository.findById(education.getId()).orElse(null),
                "Saved education must exist in the DB"
        );
    }

    @Test
    void testSaveInvalidEducation() {

        Education invalidEducation = new Education(
                null,
                "",
                "Bachelor of Testing",
                LocalDate.of(2020, 1, 1),
                LocalDate.of(2024, 1, 1),
                "Allowed to test the testing of tests",
                1L
        );

        assertThrows(
                ValidationException.class,
                () -> educationService.save(invalidEducation),
                "Saving an invalid education should throw an exception"
        );
    }

}
