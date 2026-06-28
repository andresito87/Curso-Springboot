package dev.andrescoder.portfoliobackend.service;

import dev.andrescoder.portfoliobackend.model.Skill;
import dev.andrescoder.portfoliobackend.repository.interfaces.ISkillRepository;
import dev.andrescoder.portfoliobackend.service.interfaces.ISkillService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
// Reinicia el estado de la BD antes de cada test
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class SkillServiceTest {
    @Autowired
    private ISkillService skillService;
    @Autowired
    private ISkillRepository skillRepository;

    @Test
    void testSaveValidSkill() {
        Skill skill = new Skill(null, "Java", 90, "fab fa-java", 1L);
        Skill savedSkill = skillService.save(skill);

        assertNotNull(savedSkill.getId(), "Saved skill must have an ID");
        assertNotNull(skillRepository.findById(savedSkill.getId()).orElse(null), "Saved skill must exist in the DB");
    }

    @Test
    void testFindSkillsByPersonalInfoIdReturnsSeedData() {
        var skills = skillService.findSkillsByPersonalInfoId(1L);

        assertFalse(skills.isEmpty(), "Seeded skills for personal info 1 must exist");
        assertTrue(skills.stream().allMatch(skill -> skill.getPersonalInfoId().equals(1L)));
    }
}
