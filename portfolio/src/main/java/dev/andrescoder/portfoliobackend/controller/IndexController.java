package dev.andrescoder.portfoliobackend.controller;

import dev.andrescoder.portfoliobackend.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final IPersonalInfoService personalInfoService;
    private final IEducationService educationService;
    private final ISkillService skillService;
    private final IExperienceService experienceService;
    private final IProjectService projectService;

    @GetMapping("/")
    public String showIndex(Model model) {

        model.addAttribute("personalInfo", personalInfoService.findAll().getFirst());
        model.addAttribute("experiencesList", experienceService.findAll());
        model.addAttribute("educationsList", educationService.findAll());
        model.addAttribute("skillsList", skillService.findAll());
        model.addAttribute("projectsList", projectService.findAll());
        return "index";
    }

}
