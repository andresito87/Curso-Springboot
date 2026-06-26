package dev.andrescoder.portfoliobackend.rest;

import dev.andrescoder.portfoliobackend.model.Education;
import dev.andrescoder.portfoliobackend.model.Experience;
import dev.andrescoder.portfoliobackend.model.PersonalInfo;
import dev.andrescoder.portfoliobackend.repository.IExperienceRepository;
import dev.andrescoder.portfoliobackend.service.IExperienceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/experience")
public class ExperienceController {

    private final IExperienceService experienceService;

    public ExperienceController(IExperienceService experienceService) {
        this.experienceService = experienceService;
    }

    @GetMapping
    public List<Experience> getAll() {
        return experienceService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Experience> getById(@PathVariable Long id) {
        Optional<Experience> experience = experienceService.findById(id);
        return experience.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Experience create(@RequestBody Experience experience) {
        return experienceService.save(experience);
    }

    @PutMapping("/{id}")
    public Experience update(@PathVariable Long id, @RequestBody Experience experience) {
        experience.setId(id);
        return experienceService.save(experience);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        experienceService.deleteById(id);
    }

}
