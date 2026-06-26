package dev.andrescoder.portfoliobackend.controller;

import dev.andrescoder.portfoliobackend.dto.SkillDto;
import dev.andrescoder.portfoliobackend.mapper.SkillMapper;
import dev.andrescoder.portfoliobackend.model.Skill;
import dev.andrescoder.portfoliobackend.service.ISkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/skills")
@RequiredArgsConstructor
public class SkillPageController {

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
        model.addAttribute("skill", new SkillDto());
        return "skills/form";
    }
}

