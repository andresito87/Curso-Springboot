package dev.andrescoder.portfoliobackend.controller;

import dev.andrescoder.portfoliobackend.dto.EducationDto;
import dev.andrescoder.portfoliobackend.mapper.EducationMapper;
import dev.andrescoder.portfoliobackend.model.Education;
import dev.andrescoder.portfoliobackend.service.interfaces.IEducationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/educations")
@RequiredArgsConstructor
public class EducationController {

    private final IEducationService educationService;

    @GetMapping
    public String getAll(Model model) {

        List<Education> educations = educationService.findAll();
        List<EducationDto> educationDtos = educations.stream().map(EducationMapper::toDto).toList();
        model.addAttribute("educations", educationDtos);

        return "educations/list";
    }

    @GetMapping("/new")
    public String showForm(Model model) {

        EducationDto newEducationDto = new EducationDto();
        newEducationDto.setStartDate(LocalDate.now()); // Buena practica inicializar si la el campo fecha es obligatorio
        model.addAttribute("educationDto", newEducationDto);

        return "educations/form";
    }


    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("educationDto") EducationDto educationDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "educations/form";
        }
        try {
            Education education = EducationMapper.toEntity(educationDto);
            educationService.save(education);
            return "redirect:/educations";
        } catch (Exception e) {
            return "error-page";
        }
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Optional<Education> educationOptional = educationService.findById(id);
        if (educationOptional.isEmpty()) {
            model.addAttribute("errorMessage", "Education not found with ID: " + id);
            return "redirect:/educations";
        }

        Education education = educationOptional.get();
        model.addAttribute("educationDto", EducationMapper.toDto(education));
        return "educations/form";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            educationService.deleteById(id);
            redirectAttributes.addFlashAttribute("message", "Education deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute(
                    "error",
                    "Error deleting education with id " + id + ": " + e.getMessage()
            );
        }

        return "redirect:/educations";
    }

    @GetMapping("/personal/{personalInfoId}")
    public String getEducationsByPersonalInfo(@PathVariable Long personalInfoId, Model model) {
        List<Education> educationList = educationService.findEducationsByPersonalInfoId(personalInfoId);
        List<EducationDto> educationDtos = educationList.stream().map(EducationMapper::toDto).toList();
        model.addAttribute("educationList", educationDtos);
        return "educations/list";
    }
}
