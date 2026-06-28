package dev.andrescoder.portfoliobackend.rest;

import dev.andrescoder.portfoliobackend.model.PersonalInfo;
import dev.andrescoder.portfoliobackend.service.interfaces.IPersonalInfoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/test-personal-info")
public class PersonalInfoTestController {

    private final IPersonalInfoService personalInfoService;

    public PersonalInfoTestController(IPersonalInfoService personalInfoService) {
        this.personalInfoService = personalInfoService;
    }

    @GetMapping("/all")
    public List<PersonalInfo> getAll() {
        return personalInfoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonalInfo> getById(@PathVariable Long id) {
        Optional<PersonalInfo> personalInfo = personalInfoService.findById(id);
        return personalInfo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PersonalInfo> create(@RequestBody PersonalInfo personalInfo) {
        PersonalInfo newPersonalInfo = personalInfoService.save(personalInfo);
        return new ResponseEntity<>(newPersonalInfo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonalInfo> update(@PathVariable Long id, @RequestBody PersonalInfo personalInfo) {
        Optional<PersonalInfo> existingPersonalInfo = personalInfoService.findById(id);
        if (existingPersonalInfo.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "PersonalInfo not found with id: " + id);
        }
        personalInfo.setId(id);
        PersonalInfo updatedPersonalInfo = personalInfoService.save(personalInfo);
        return new ResponseEntity<>(updatedPersonalInfo, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PersonalInfo> delete(@PathVariable Long id) {
        Optional<PersonalInfo> existingPersonalInfo = personalInfoService.findById(id);
        if (existingPersonalInfo.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "PersonalInfo not found with id: " + id);
        }
        personalInfoService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
