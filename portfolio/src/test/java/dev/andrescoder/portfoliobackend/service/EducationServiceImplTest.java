package dev.andrescoder.portfoliobackend.service;

import dev.andrescoder.portfoliobackend.exception.ValidationException;
import dev.andrescoder.portfoliobackend.model.Education;
import dev.andrescoder.portfoliobackend.repository.interfaces.IEducationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Permite utilizar Mockito para usar mocks de las dependencias de la implementación
class EducationServiceImplTest {

    @Mock // Genera un mock del repositorio Skill
    private IEducationRepository educationRepository;

    @Mock
    private Validator validator;

    @InjectMocks // Inyecta el mock del repositorio en la clase implementada SkillServiceImpl
    private EducationServiceImpl educationService;

    @Test
    void testFindAllReturnsListOfEducations() {

        // Arrange
        List<Education> mockedEducations = Arrays.asList(
                new Education(
                        1L,
                        "Computer Science",
                        "MIT",
                        LocalDate.of(2020, 1, 1),
                        LocalDate.of(2024, 1, 1),
                        "Description",
                        1L
                ), new Education(
                        2L,
                        "Mathematics",
                        "Harvard",
                        LocalDate.of(2018, 1, 1),
                        LocalDate.of(2022, 1, 1),
                        "Description",
                        1L
                )
        );
        when(educationRepository.findAll()).thenReturn(mockedEducations);

        // Action
        List<Education> educations = educationService.findAll();

        // Assert
        assertNotNull(educations);
        assertEquals(2, educations.size());
        assertTrue(educations.contains(educations.get(0)));
        assertTrue(educations.contains(educations.get(1)));
        verify(educationRepository, times(1)).findAll();

    }

    @Test
    void testFindByIdReturnsEducationWhenFound() {

        // Arrange
        Long id = 1L;
        Education mockedEducation = new Education(
                id,
                "Computer Science",
                "MIT",
                LocalDate.of(2020, 1, 1),
                LocalDate.of(2024, 1, 1),
                "Description",
                1L
        );
        when(educationRepository.findById(id)).thenReturn(Optional.of(mockedEducation));

        // Action
        Optional<Education> education = educationService.findById(id);

        // Assert
        assertTrue(education.isPresent());
        assertEquals(mockedEducation, education.get());
        verify(educationRepository, times(1)).findById(id);

    }

    @Test
    void testSaveEducationThrowsExceptionWhenInvalid() {

        // Arrange
        Education invalidEducation = new Education();
        doAnswer(invocationOnMock -> {
            BindingResult result = invocationOnMock.getArgument(1);
            result.rejectValue("degree", "NotBlank", "El título no puede estar vacío");
            return null;
        }).when(validator).validate(any(Education.class), any(BindingResult.class));

        // Act & Assert
        assertThrows(
                ValidationException.class,
                () -> educationService.save(invalidEducation),
                "Debe lanzarse una ValidationException si el objeto no es válido."
        );
        verify(educationRepository, never()).save(any(Education.class));

    }

    @Test
    void testSaveEducationSavesValidEducation() {

        // Arrange
        Education validEducation = new Education(
                null,
                "Ingeniería en Desarrollo de Software",
                "UMA",
                LocalDate.of(2015, 3, 1),
                LocalDate.of(2020, 12, 1),
                "Descripción de la carrera",
                1L
        );
        when(educationRepository.save(any(Education.class))).thenReturn(validEducation);
        doNothing().when(validator).validate(any(Education.class), any(BindingResult.class));

        // Act
        Education savedEducation = educationService.save(validEducation);

        // Assert
        assertNotNull(savedEducation);
        verify(educationRepository, times(1)).save(validEducation);

    }

}