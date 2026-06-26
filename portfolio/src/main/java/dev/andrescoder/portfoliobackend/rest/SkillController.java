package dev.andrescoder.portfoliobackend.rest;

import dev.andrescoder.portfoliobackend.model.Skill;
import dev.andrescoder.portfoliobackend.service.ISkillService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/skill")
public class SkillController {

    private final ISkillService skillService;

    public SkillController(ISkillService skillService) {
        this.skillService = skillService;
    }

    @GetMapping
    public List<Skill> getAll() {
        return skillService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Skill> getById(@PathVariable Long id) {
        return skillService.findById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/personal-info/{personalInfoId}")
    public List<Skill> getAll(@PathVariable Long personalInfoId) {
        return skillService.findSkillsByPersonalInfoId(personalInfoId);
    }

    @PostMapping
    public Skill create(@RequestBody Skill skill) {
        return skillService.save(skill);
    }

    @PutMapping("/{id}")
    public Skill update(@PathVariable Long id, @RequestBody Skill skill) {
        skill.setId(id);
        return skillService.save(skill);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        skillService.deleteById(id);
    }
}
