package dev.andrescoder.portfoliobackend.exception.handler;

import dev.andrescoder.portfoliobackend.exception.ValidationException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    public String handleValidationException(ValidationException ex, Model model) {

        model.addAttribute("errors", ex.getBindingResult().getAllErrors());
        model.addAttribute("message", "Validation errors found");

        return "error/validation";
    }

}
