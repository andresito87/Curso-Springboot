package dev.andrescoder.portfoliobackend.rest;

import dev.andrescoder.portfoliobackend.model.Education;
import dev.andrescoder.portfoliobackend.service.IEducationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/education")
public class EducationController {

    private final IEducationService educationService;

    public EducationController(IEducationService educationService) {
        this.educationService = educationService;
    }

    @GetMapping
    public List<Education> getAll() {
        return educationService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Education> getById(@PathVariable Long id) {
        Optional<Education> education = educationService.findById(id);
        return education.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Education create(@RequestBody Education education) {
        return educationService.save(education);
    }

    @PutMapping("/{id}")
    public Education update(@PathVariable Long id, @RequestBody Education education) {
        education.setId(id);
        return educationService.save(education);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        educationService.deleteById(id);
    }

}
