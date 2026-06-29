package dev.andrescoder.portfoliobackend.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLoginForm(@RequestParam(value = "error", required = false) String error,
                                @RequestParam(value = "logout", required = false) String logout, Model model,
                                Authentication authentication) {

        // Comprueba a través del bean de Spring si el usuario está autenticado.
        // Si lo está, redirige a la página de información personal implidiendo mostrar el login de nuevo
        if (authentication != null && authentication.isAuthenticated()) {
            return "redirect:/personal-info";
        }

        // Va comprobar si en la url hay un request param ?error= para mostrar el error de login
        if (error != null) {
            model.addAttribute("error", "El usuario o la contraseña no son válidos");
        }

        if (logout != null) {
            model.addAttribute("message", "La sesión se ha cerrado correctamente");
        }

        return "auth/form-login";
    }

}
