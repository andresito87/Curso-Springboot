package dev.andrescoder.portfoliobackend.controller;

import dev.andrescoder.portfoliobackend.dto.ProjectDto;
import dev.andrescoder.portfoliobackend.mapper.ProjectMapper;
import dev.andrescoder.portfoliobackend.model.Project;
import dev.andrescoder.portfoliobackend.service.FileStorageService;
import dev.andrescoder.portfoliobackend.service.interfaces.IProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/projects")
public class ProjectController {

    private final IProjectService projectService;
    private final FileStorageService fileStorageService;

    @GetMapping
    public String getAll(Model model) {
        List<ProjectDto> projects = projectService.findAll().stream().map(ProjectMapper::toDto).toList();
        model.addAttribute("projects", projects);
        return "projects/list";
    }

    @GetMapping("/new")
    public String formNewProject(Model model) {
        model.addAttribute("projectDto", new ProjectDto());
        return "projects/form";
    }

    @PostMapping("/save")
    // @Valid aplica las validaciones del Dto
    // @ModelAttribute recoge los datos del formulario y los encapsula en un Dto para luego inyectarlo en save
    // MultipartFile permite manejar archivos en la petición
    public String save(@Valid @ModelAttribute("projectDto") ProjectDto projectDto, BindingResult bindingResult,
                       @RequestParam("file") MultipartFile file) {


        // Comprueba si el archivo está vacío y agrega un error al BindingResult si es así
        if (file.isEmpty()) {
            bindingResult.rejectValue("imageUrl", "file.required", "Image is required");
        }

        // Si hay algún problema en los datos o archivos redirecciona a la página de formulario para mostrar los errores
        if (bindingResult.hasErrors()) {
            return "projects/form";
        }

        Project project = ProjectMapper.toEntity(projectDto);
        try {
            String imageUrl = fileStorageService.storeFile(file);
            project.setImageUrl(imageUrl);
            projectService.save(project);

            return "redirect:/projects";
        } catch (IOException e) {
            return "error-page";
        }
    }

}
