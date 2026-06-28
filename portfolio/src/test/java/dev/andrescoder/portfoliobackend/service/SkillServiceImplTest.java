package dev.andrescoder.portfoliobackend.service;

import dev.andrescoder.portfoliobackend.model.Skill;
import dev.andrescoder.portfoliobackend.repository.interfaces.ISkillRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Permite utilizar Mockito para generar mocks de las dependencias
public class SkillServiceImplTest {

    @Mock // Genera un mock del repositorio Skill
    private ISkillRepository skillRepository;

    @InjectMocks // Inyecta el mock del repositorio en la clase implementada SkillServiceImpl
    private SkillServiceImpl skillService;

    @Test
    public void testFindAllReturnsListOfSkills() {

        List<Skill> mockedSkills = Arrays.asList(
                new Skill(1L, "Java", 90, "fa fab-java", 1L),
                new Skill(2L, "Python", 80, "fa fab-python", 1L)
        );
        when(skillRepository.findAll()).thenReturn(mockedSkills);

        List<Skill> skills = skillService.findAll();

        assertNotNull(skills);
        assertEquals(2, skills.size());
        assertTrue(skills.contains(skills.get(0)));
        assertTrue(skills.contains(skills.get(1)));
        verify(skillRepository, times(1)).findAll();

    }

    @Test
    public void testFindByIdReturnsSkillWhenFound() {

        Long id = 1L;
        Optional<Skill> mockedSkill = Optional.of(new Skill(id, "Java", 90, "fa fab-java", 1L));
        when(skillRepository.findById(id)).thenReturn(mockedSkill);

        Optional<Skill> skill = skillService.findById(id);

        assertTrue(skill.isPresent());
        assertEquals(mockedSkill, skill);
        verify(skillRepository, times(1)).findById(id);

    }

    @Test
    public void testFindSkillsByPersonalInfoIdReturnsSkills() {

        Long personalInfoId = 1L;
        List<Skill> mockedSkills = Arrays.asList(
                new Skill(1L, "Java", 90, "fa fab-java", personalInfoId),
                new Skill(2L, "Python", 80, "fa fab-python", personalInfoId)
        );
        when(skillRepository.findSkillsByPersonalInfoId(personalInfoId)).thenReturn(mockedSkills);

        List<Skill> skills = skillService.findSkillsByPersonalInfoId(personalInfoId);

        assertNotNull(skills);
        assertEquals(2, skills.size());
        verify(skillRepository, times(1)).findSkillsByPersonalInfoId(personalInfoId);

    }

    @Test
    public void testSaveSkillSavesValidSkill() {

        // Preparación
        Long id = 1L;
        Skill validSkill = new Skill(id, "Java", 90, "fa fab-java", 1L);
        when(skillRepository.save(any(Skill.class))).thenReturn(validSkill);

        // Acción
        Skill savedSkill = skillService.save(validSkill);

        // Verificación
        assertNotNull(savedSkill);
        assertEquals(validSkill, savedSkill);
        verify(skillRepository, times(1)).save(validSkill);

    }
}
