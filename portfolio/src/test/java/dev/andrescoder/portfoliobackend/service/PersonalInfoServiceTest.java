package dev.andrescoder.portfoliobackend.service;

import dev.andrescoder.portfoliobackend.model.PersonalInfo;
import dev.andrescoder.portfoliobackend.repository.interfaces.IPersonalInfoRepository;
import dev.andrescoder.portfoliobackend.service.interfaces.IPersonalInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class PersonalInfoServiceTest {

    @Autowired
    private IPersonalInfoService personalInfoService;
    @Autowired
    private IPersonalInfoRepository personalInfoRepository;

    @Test
    void testSaveValidPersonalInfo() {
        PersonalInfo validInfo = new PersonalInfo(
                null,
                "John",
                "Doe",
                "Developer",
                "email@example.com",
                "123456789",
                "Descripcion",
                "email@example.com",
                5,
                "https://linkedin.com",
                "https://github.com"
        );
        PersonalInfo savedInfo = personalInfoService.save(validInfo);

        assertNotNull(savedInfo.getId(), "Saved info must have an ID");
        assertNotNull(
                personalInfoRepository.findById(savedInfo.getId()).orElse(null),
                "Saved info must exist in the DB"
        );
    }

    @Test
    void testFindByIdReturnsSeededPersonalInfo() {
        var personalInfo = personalInfoService.findById(1L);

        assertTrue(personalInfo.isPresent(), "Seeded personal info with ID 1 must exist");
        assertNotNull(personalInfo.get().getFirstName(), "Seeded personal info must include a first name");
    }

}
