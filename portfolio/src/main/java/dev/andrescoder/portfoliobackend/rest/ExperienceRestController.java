package dev.andrescoder.portfoliobackend.rest;

import dev.andrescoder.portfoliobackend.model.Experience;
import dev.andrescoder.portfoliobackend.service.interfaces.IExperienceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/experience")
public class ExperienceRestController {

    private final IExperienceService experienceService;

    public ExperienceRestController(IExperienceService experienceService) {
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
