package dev.andrescoder.cv_springboot.cv.rest;

import dev.andrescoder.cv_springboot.cv.model.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CvApiController {

    @GetMapping("/cv")
    public Person getPerson() {
        return new Person("Andrés","Podadera","Desarrollador");
    }
}
