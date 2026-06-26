package dev.andrescoder.portfoliobackend.service;

import dev.andrescoder.portfoliobackend.exception.ValidationException;
import dev.andrescoder.portfoliobackend.model.Skill;
import dev.andrescoder.portfoliobackend.repository.ISkillRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Permite utilizar Mockito para generar mocks de las dependencias
public class SkillServiceImplTest {

    @Mock // Genera un mock del repositorio Skill
    private ISkillRepository skillRepository;

    @Mock
    private Validator validator;

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
    public void testSaveSkillThrowsExceptionWhenInvalid() {

        Long id = 1L;
        String messageException = "Name must not be blank";
        Optional<Skill> mockedSkill = Optional.of(new Skill(id, "", 90, "fa fab-java", 1L));
        doAnswer(invocation -> {
            BindingResult result = invocation.getArgument(1);
            result.rejectValue("name", "NotBlank", messageException);
            return null;
        }).when(validator).validate(any(Skill.class), any(BindingResult.class));

        ValidationException exception = assertThrows(
                ValidationException.class,
                () -> skillService.save(mockedSkill.get())
        );

        assertTrue(exception.getMessage().contains(messageException));
        verify(skillRepository, never()).save(any(Skill.class));

    }

    @Test
    public void testSaveSkillSavesValidSkill() {

        // Preparación
        Long id = 1L;
        Skill validSkill = new Skill(id, "Java", 90, "fa fab-java", 1L);
        when(skillRepository.save(any(Skill.class))).thenReturn(validSkill);
        doNothing().when(validator).validate(any(Skill.class), any(BindingResult.class));

        // Acción
        Skill savedSkill = skillService.save(validSkill);

        // Verificación
        assertNotNull(savedSkill);
        assertEquals(validSkill, savedSkill);
        verify(skillRepository, times(1)).save(validSkill);

    }
}
