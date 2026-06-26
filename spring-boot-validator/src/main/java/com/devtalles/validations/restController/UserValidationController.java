package com.devtalles.validations.restController;

import com.devtalles.validations.model.User;
import com.devtalles.validations.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserValidationController {

    private final UserService userService;

    @PostMapping("/validate-and-save")
    public ResponseEntity<String> validateAndSaveUser(@Valid @RequestBody User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
            return ResponseEntity.badRequest().body("Errores de validación: " + String.join(", ", errors));
        }
        userService.saveUser(user);
        return ResponseEntity.ok("Usuario validado y guardado correctamente.");
    }

}
