package dev.andrescoder.portfoliobackend.service;

import dev.andrescoder.portfoliobackend.exception.ValidationException;
import dev.andrescoder.portfoliobackend.model.PersonalInfo;
import dev.andrescoder.portfoliobackend.repository.IPersonalInfoRepository;
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
public class PersonalInfoServiceImpl implements IPersonalInfoService {

    private final IPersonalInfoRepository personalInfoRepository;
    private final Validator validator;

    @Override
    @Transactional // el 'save' se ejecuta como una unidad atómica ACID(hace el commit si esta OK o el rollback)
    public PersonalInfo save(PersonalInfo personalInfo) {
        // Utiliza la validación que se realiza con anotaciones en los modelos
        BindingResult result = new BeanPropertyBindingResult(personalInfo, "personalInfo");
        validator.validate(personalInfo, result);

        if (result.hasErrors()) {
            throw new ValidationException(result);
        }

        return personalInfoRepository.save(personalInfo);
    }

    @Override
    @Transactional(readOnly = true) // Son transacciones pero de sólo lectura, sin modificaciones en los datos a DB
    public Optional<PersonalInfo> findById(Long id) {
        return personalInfoRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true) // Son transacciones pero de sólo lectura, sin modificaciones en los datos a DB
    public List<PersonalInfo> findAll() {
        return personalInfoRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        personalInfoRepository.deleteById(id);
    }
}
