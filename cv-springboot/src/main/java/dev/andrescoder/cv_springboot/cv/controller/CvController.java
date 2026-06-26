package dev.andrescoder.cv_springboot.cv.controller;

import dev.andrescoder.cv_springboot.cv.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cv")
public class CvController {

    @GetMapping({"","/","/index"})
    public String index(Model model) {
        Person person = new Person("Andrés","Podadera","Dev");
        model.addAttribute("name","Andrés");
        model.addAttribute("persona",person);
        return "index";
    }

}
