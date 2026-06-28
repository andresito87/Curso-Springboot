package dev.andrescoder.portfoliobackend.controller;

import dev.andrescoder.portfoliobackend.dto.PersonalInfoDto;
import dev.andrescoder.portfoliobackend.mapper.PersonalInfoMapper;
import dev.andrescoder.portfoliobackend.model.PersonalInfo;
import dev.andrescoder.portfoliobackend.service.interfaces.IPersonalInfoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/personal-info")
@RequiredArgsConstructor
public class PersonalInfoController {

    private final IPersonalInfoService personalInfoService;

    // Dado que suele haber una única PersonalInfo, a menudo se usa un ID fijo (ej. 1L)
    // se redirige directamente al formulario de edición si se accede a "/personal-info"
    private static final Long DEFAULT_PERSONAL_INFO_ID = 1L; // Asumimos un ID por defecto

    @GetMapping
    public String viewPersonalInfo() {
        return "redirect:/personal-info/edit/" + DEFAULT_PERSONAL_INFO_ID;
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<PersonalInfo> personalInfoOptional = personalInfoService.findById(id);
        if (personalInfoOptional.isPresent()) {
            PersonalInfoDto personalInfoDto = PersonalInfoMapper.toDto(personalInfoOptional.get());
            model.addAttribute("personalInfoDto", personalInfoDto);
        } else {
            // Asumimos que siempre va a existir un PersonalInfo y si no, se crea una en blanco para rellenar.
            model.addAttribute("personalInfoDto", new PersonalInfoDto());
            model.addAttribute("error", "No personal info found with ID " + id + ". You can create a new one.");
        }
        return "personalinfo/form"; // Redirigimos a un formulario de edición/creación
    }

    // La ruta save manejará tanto la creación (id == null) como la actualización (id != null).
    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("personalInfoDto") PersonalInfoDto personalInfoDto, BindingResult result,
                       Model model, RedirectAttributes redirectAttributes) {

        // Utiliza la validación que se realiza con anotaciones en los dtos
        if (result.hasErrors()) {
            return "personalinfo/form";
        }

        try {
            PersonalInfo personalInfo = PersonalInfoMapper.toEntity(personalInfoDto);
            personalInfoService.save(personalInfo);
            redirectAttributes.addFlashAttribute("message", "Personal info saved successfully!");
            return "redirect:/personal-info/edit/" + personalInfo.getId(); // Redirige a la edición de la misma info
        } catch (Exception e) {
            model.addAttribute("error", "Error saving personal info: " + e.getMessage());
            // Si hubo un error, volvemos al formulario para que el usuario pueda corregir
            // Y mantenemos el DTO en el modelo para que los datos ingresados no se pierdan.
            // Por ello no usamos redirect
            return "personalinfo/form";
        }
    }

    // Endpoint para crear la primera PersonalInfo si no existe ninguna.
    // Útil para la primera vez que se accede al sistema.
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("personalInfoDto", new PersonalInfoDto());
        return "personalinfo/form";
    }

}