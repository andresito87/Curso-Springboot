package dev.andrescoder.portfoliobackend.controller;

import dev.andrescoder.portfoliobackend.dto.SkillDto;
import dev.andrescoder.portfoliobackend.mapper.SkillMapper;
import dev.andrescoder.portfoliobackend.model.Skill;
import dev.andrescoder.portfoliobackend.service.interfaces.ISkillService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/skills")
@RequiredArgsConstructor
public class SkillController {

    private final ISkillService skillService;

    @GetMapping
    public String getAll(Model model) {

        List<Skill> skills = skillService.findAll();

        List<SkillDto> skillDtos = skills.stream().map(SkillMapper::toDto).toList();

        model.addAttribute("skills", skillDtos);
        return "skills/list";
    }

    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("skillDto", new SkillDto());
        return "skills/form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("skillDto") SkillDto skillDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "skills/form";
        }

        try {
            Skill skill = SkillMapper.toEntity(skillDto);
            skillService.save(skill);
            return "redirect:/skills";
        } catch (Exception e) {
            return "error-page";
        }
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {

        Optional<Skill> skill = skillService.findById(id);
        if (skill.isEmpty()) {
            model.addAttribute("errorMessage", "Skill not found" + id);
            return "redirect:/skills";
        }

        SkillDto skillDto = SkillMapper.toDto(skill.get()); // Transforma a un SkillDto para enviar los datos
        model.addAttribute("skillDto", skillDto); // Agrega los datos al model que utiliza la vista
        return "skills/form"; // Retorna la vista con los datos para mostrarlos al usuario
    }

    @GetMapping("/personal/{personalInfoId}")
    public String getSkillsByPersonalInfoId(@PathVariable Long personalInfoId, Model model) {
        List<Skill> skills = skillService.findSkillsByPersonalInfoId(personalInfoId); // Obtiene las skills de la DB
        List<SkillDto> skillDtos = skills.stream().map(SkillMapper::toDto).toList(); // Las transforma a dtos
        model.addAttribute("skills", skillDtos); // Envia los datos en forma de dtos a la vista
        return "skills/list"; // Devuelve la vista con los datos de las skills del personalInfoId
    }

    // Utilizamos un PostMapping para eliminar, ya que algunos navegadores no soportan DeleteMapping en los
    // method="post" de los formularios. En el contexto de un RestController, no estamos condicionados por formularios,
    // así que no tendríamos este requisito y por tanto debemos usar DeleteMapping.
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {

        try {
            skillService.deleteById(id);
            // Permite enviar un mensaje de éxito a la vista después de la redirección
            redirectAttributes.addFlashAttribute("successMessage", "Skill deleted successfully");
        } catch (Exception e) {
            // Permite enviar un mensaje de error a la vista después de la redirección
            redirectAttributes.addFlashAttribute("errorMessage", "Error deleting skill: " + e.getMessage());
        }

        return "redirect:/skills";
    }
}
