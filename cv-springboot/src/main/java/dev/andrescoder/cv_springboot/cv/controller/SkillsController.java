package dev.andrescoder.cv_springboot.cv.controller;

import dev.andrescoder.cv_springboot.cv.model.Skill;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/skills")
public class SkillsController {

    private final List<Skill> skills = new ArrayList<>(
            List.of(
                    new Skill("C++", "Avanzado"),
                    new Skill("Java", "Intermedio"),
                    new Skill("Spring Boot", "Básico"),
                     new Skill("Html", "Avanzado")
            )
    );

    @GetMapping
    public String showSkills(@RequestParam(defaultValue = "", required = false) String filter, Model model) { // RequestParam permite utilizar query params en la url
        List<Skill> skillsFiltered = this.skills.stream()
                .filter(skill -> skill.getName().toLowerCase().contains(filter.toLowerCase()))
                .toList();

        model.addAttribute("skills", skillsFiltered);
        model.addAttribute("filter", filter);
        return "skills";
    }

    @GetMapping("/id/{index}")
    public String showSkillDetail(@PathVariable int index, Model model){ // PathVariable permite que el parámetro sea parte de la url
        if(index >= 0 && index < skills.size()){
            Skill skill = skills.get(index);
            model.addAttribute("skill", skill);
            return "skill-detail";
        }

        return "redirect:/skills";
    }

    @GetMapping("/name/{name}")
    public String showFilteredSkill(@PathVariable String name, Model model){ // PathVariable permite que el parámetro sea parte de la url
        List<Skill> skillsFiltered = this.skills.stream()
                .filter(skill -> skill.getName().equalsIgnoreCase(name))
                .toList();

        if(skillsFiltered.isEmpty()){
            model.addAttribute("filterMessage", "No se encontraron habilidades con el nombre: " + name);

            return "forward:/skills"; // No hace una nueva petición, reutiliza el objeto con los datos y carga la vista con ellos, no cambia la url
        }

        model.addAttribute("skills", skillsFiltered);
        model.addAttribute("filterMessage", "Filtro: " + name);
        return "skills";
    }

    @GetMapping("/filter/{name}/{level}")
    public String showFilteredSkill(@PathVariable String name,@PathVariable String level, Model model){ // PathVariable permite que el parámetro sea parte de la url
        List<Skill> skillsFiltered = this.skills.stream()
                .filter(skill -> skill.getName().equalsIgnoreCase(name) && skill.getLevel().equalsIgnoreCase(level))
                .toList();
        model.addAttribute("skills", skillsFiltered);
        model.addAttribute("filterMessage", "Filtro: " + name + " - " + level);
        return "skills";
    }

    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("skill", new Skill());
        return "add-skill";
    }

    @PostMapping("/add")
    public String addSkill(@ModelAttribute Skill skill) { // ModelAtribute sirve para vincular un formulario con un objeto Java
        skills.add(skill);
        return "redirect:/skills";
    }
}
