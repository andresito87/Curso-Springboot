package dev.andrescoder.portfoliobackend.service;

import dev.andrescoder.portfoliobackend.exception.ValidationException;
import dev.andrescoder.portfoliobackend.model.Experience;
import dev.andrescoder.portfoliobackend.repository.IExperienceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import java.util.List;
import java.util.Optional;

@Service
public class ExperienceServiceImpl implements IExperienceService {

    private final IExperienceRepository experienceRepository;
    private final Validator validator;

    public ExperienceServiceImpl(IExperienceRepository experienceRepository, Validator validator) {
        this.experienceRepository = experienceRepository;
        this.validator = validator;
    }

    @Override
    @Transactional
    public Experience save(Experience experience) throws IllegalArgumentException {

        // Utiliza la validación que se realiza con anotaciones en los modelos
        BindingResult result = new BeanPropertyBindingResult(experience, "experience");
        validator.validate(experience, result);

        if (result.hasErrors() || (experience.getEndDate() != null && experience.getStartDate().isAfter(experience.getEndDate()))) {
            throw new ValidationException(result);
        }

        return experienceRepository.save(experience);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Experience> findById(Long id) {
        return experienceRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Experience> findAll() {
        return experienceRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        experienceRepository.deleteById(id);
    }

    @Override
    @Transactional
    public List<Experience> findExperiencesByPersonalInfoId(Long personalInfoId) {
        return experienceRepository.findExperiencesByPersonalInfoId(personalInfoId);
    }
}
