package dev.andrescoder.portfoliobackend.controller;

import dev.andrescoder.portfoliobackend.dto.ExperienceDto;
import dev.andrescoder.portfoliobackend.mapper.ExperienceMapper;
import dev.andrescoder.portfoliobackend.model.Experience;
import dev.andrescoder.portfoliobackend.service.interfaces.IExperienceService;
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
@RequestMapping("/experiences")
@RequiredArgsConstructor
public class ExperienceController {

    private final IExperienceService experienceService;

    @GetMapping
    public String getAll(Model model) {

        List<Experience> experiences = experienceService.findAll();
        List<ExperienceDto> experienceDtos = experiences.stream().map(ExperienceMapper::toDto).toList();
        model.addAttribute("experiences", experienceDtos);

        return "experiences/list";

    }

    @GetMapping("/new")
    public String showForm(Model model) {

        ExperienceDto newExperienceDto = new ExperienceDto();
        // Buena practica inicializar si la el campo fecha es obligatorio
        newExperienceDto.setStartDate(LocalDate.now());
        model.addAttribute("experienceDto", newExperienceDto);

        return "experiences/form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("experienceDto") ExperienceDto experienceDto, BindingResult bindingResult,
                       RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "experiences/form";
        }
        try {
            Experience experience = ExperienceMapper.toEntity(experienceDto);
            experienceService.save(experience);
            return "redirect:/experiences";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error saving experience with id " + e.getMessage());
            return "error-page";
        }
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {

        Optional<Experience> experience = experienceService.findById(id);
        if (experience.isEmpty()) {
            model.addAttribute("errorMessage", "Experience not found" + id);
            return "redirect:/experiences";
        }

        // Transforma a un ExperienceDto para enviar los datos
        ExperienceDto experienceDto = ExperienceMapper.toDto(experience.get());
        model.addAttribute("experienceDto", experienceDto); // Agrega los datos al model que utiliza la vista
        return "experiences/form"; // Retorna la vista con los datos para mostrarlos al usuario
    }

    @GetMapping("/personal/{personalInfoId}")
    public String getExperiencesByPersonalInfoId(@PathVariable Long personalInfoId, Model model) {
        // Obtiene las experiences de la DB
        List<Experience> experiences = experienceService.findExperiencesByPersonalInfoId(personalInfoId);
        List<ExperienceDto> experienceDtos = experiences.stream().map(ExperienceMapper::toDto)
                .toList(); // Las transforma a dtos
        model.addAttribute("experiences", experienceDtos); // Envia los datos en forma de dtos a la vista
        return "experiences/list"; // Devuelve la vista con los datos de las experiences del personalInfoId
    }

    // Utilizamos un PostMapping para eliminar, ya que algunos navegadores no soportan DeleteMapping en los
    // method="post" de los formularios. En el contexto de un RestController, no estamos condicionados por formularios,
    // así que no tendríamos este requisito y por tanto debemos usar DeleteMapping.
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {

        try {
            experienceService.deleteById(id);
            // Permite enviar un mensaje de éxito a la vista después de la redirección
            redirectAttributes.addFlashAttribute("successMessage", "Experience deleted successfully");
        } catch (Exception e) {
            // Permite enviar un mensaje de error a la vista después de la redirección
            redirectAttributes.addFlashAttribute("errorMessage", "Error deleting experience: " + e.getMessage());
        }

        return "redirect:/experiences";
    }

}
