package dev.andrescoder.portfoliobackend.service;

import dev.andrescoder.portfoliobackend.model.Skill;
import dev.andrescoder.portfoliobackend.repository.interfaces.ISkillRepository;
import dev.andrescoder.portfoliobackend.service.interfaces.ISkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SkillServiceImpl implements ISkillService {

    private final ISkillRepository skillRepository;

    @Override
    @Transactional
    public Skill save(Skill skill) throws IllegalArgumentException {
        return skillRepository.save(skill);
    }

    @Override
    @Transactional(readOnly = true) // Son transacciones pero de sólo lectura, sin modificaciones en los datos a DB
    public Optional<Skill> findById(Long id) {
        return skillRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true) // Son transacciones pero de sólo lectura, sin modificaciones en los datos a DB
    public List<Skill> findAll() {
        return skillRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        skillRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true) // Son transacciones pero de sólo lectura, sin modificaciones en los datos a DB
    public List<Skill> findSkillsByPersonalInfoId(Long personalInfoId) {
        return skillRepository.findSkillsByPersonalInfoId(personalInfoId);
    }
}
