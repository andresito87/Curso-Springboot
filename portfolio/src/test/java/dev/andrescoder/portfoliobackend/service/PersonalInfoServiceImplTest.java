package dev.andrescoder.portfoliobackend.service;

import dev.andrescoder.portfoliobackend.model.PersonalInfo;
import dev.andrescoder.portfoliobackend.repository.interfaces.IPersonalInfoRepository;
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
import static org.mockito.internal.verification.VerificationModeFactory.times;

@ExtendWith(MockitoExtension.class)
public class PersonalInfoServiceImplTest {

    @Mock
    private IPersonalInfoRepository personalInfoRepository;

    @InjectMocks
    private PersonalInfoServiceImpl personalInfoService;

    @Test
    void testFindAllReturnsListOfPersonalInfo() {

        // Arrange
        List<PersonalInfo> mockPersonalInfo = Arrays.asList(new PersonalInfo(), new PersonalInfo());
        when(personalInfoRepository.findAll()).thenReturn(mockPersonalInfo);

        // Act
        List<PersonalInfo> result = personalInfoService.findAll();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(personalInfoRepository, times(1)).findAll();

    }

    @Test
    void testFindByIdReturnsPersonalInfoWhenFound() {

        // Arrange
        Long personalInfoId = 1L;
        PersonalInfo mockPersonalInfo = new PersonalInfo();
        when(personalInfoRepository.findById(personalInfoId)).thenReturn(Optional.of(mockPersonalInfo));

        // Act
        Optional<PersonalInfo> result = personalInfoService.findById(personalInfoId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(mockPersonalInfo, result.get());
        verify(personalInfoRepository, times(1)).findById(personalInfoId);

    }

    @Test
    void testDeleteByIdDelegatesToRepository() {

        Long personalInfoId = 1L;

        personalInfoService.deleteById(personalInfoId);

        verify(personalInfoRepository, times(1)).deleteById(personalInfoId);

    }

    @Test
    void testSavePersonalInfoSavesValidPersonalInfo() {

        // Arrange
        PersonalInfo validPersonalInfo = new PersonalInfo(
                null,
                "John",
                "Doe",
                "Desarrollador",
                "john.doe@email.com",
                "123456789",
                "Descripción del perfil",
                "https://url.com/perfil.jpg",
                5,
                "https://linkedin.com/in/johndoe",
                "https://github.com/johndoe"
        );
        when(personalInfoRepository.save(any(PersonalInfo.class))).thenReturn(validPersonalInfo);

        // Act
        PersonalInfo savedPersonalInfo = personalInfoService.save(validPersonalInfo);

        // Assert
        assertNotNull(savedPersonalInfo);
        verify(personalInfoRepository, times(1)).save(validPersonalInfo);

    }
}
