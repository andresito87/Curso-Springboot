package dev.andrescoder.portfoliobackend.service;

import dev.andrescoder.portfoliobackend.model.PersonalInfo;
import dev.andrescoder.portfoliobackend.repository.interfaces.IPersonalInfoRepository;
import dev.andrescoder.portfoliobackend.service.interfaces.IPersonalInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonalInfoServiceImpl implements IPersonalInfoService {

    private final IPersonalInfoRepository personalInfoRepository;

    @Override
    @Transactional // el 'save' se ejecuta como una unidad atómica ACID(hace el commit si esta OK o el rollback)
    public PersonalInfo save(PersonalInfo personalInfo) {
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
