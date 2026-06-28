package dev.andrescoder.portfoliobackend.service;

import dev.andrescoder.portfoliobackend.exception.ValidationException;
import dev.andrescoder.portfoliobackend.model.Education;
import dev.andrescoder.portfoliobackend.repository.interfaces.IEducationRepository;
import dev.andrescoder.portfoliobackend.service.interfaces.IEducationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EducationServiceImpl implements IEducationService {

    private final IEducationRepository educationRepository;
    private final Validator validator;

    @Override
    @Transactional
    public Education save(Education education) throws IllegalArgumentException {

        // Utiliza la validación que se realiza con anotaciones en los dtos
        BindingResult result = new BeanPropertyBindingResult(education, "education");
        validator.validate(education, result);

        if (result.hasErrors() || (education.getEndDate() != null && education.getStartDate()
                .isAfter(education.getEndDate()))) {
            throw new ValidationException(result);
        }

        return educationRepository.save(education);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Education> findById(Long id) {
        return educationRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Education> findAll() {
        return educationRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        educationRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Education> findEducationsByPersonalInfoId(Long personalInfoId) {
        return educationRepository.findEducationsByPersonalInfoId(personalInfoId);
    }
}
