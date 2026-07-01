package dev.andrescoder.events_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

// Esta anotación permite centralizar el manejo de excepciones en un solo lugar, aplicable a todos los controladores
// de la aplicación.
@ControllerAdvice
public class GlobalExceptionHandler {

    // Este es un manejador de excepciones global para capturar errores de validación de argumentos en los
    // controladores. Los manejadores se clasifican por la excepción que manejan, en este caso,
    // MethodArgumentNotValidException. Cuando se lanza esta excepción, el method handleValidationExceptions se
    // ejecuta y construye una respuesta de error personalizada que incluye detalles sobre los errores de validación.
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException exception) {
        Map<String, Object> errors = new HashMap<>();
        exception.getBindingResult().getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

        Map<String, Object> body = new HashMap<>();
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("error", "Bad Request");
        body.put("message", "Validation Failed");
        body.put("errors", errors.toString());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    // Este es un manejador de excepciones global para capturar errores de recursos no encontrados en los
    // controladores. Los manejadores se clasifican por la excepción que manejan, en este caso,
    // ResourceNotFoundException. Cuando se lanza esta excepción, el methodo handleResourceNotFoundException se
    // ejecuta y construye una respuesta de error personalizada que incluye detalles sobre el error de recurso no
    // encontrado.
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException exception) {

        Map<String, Object> body = new HashMap<>();
        body.put("status", HttpStatus.NOT_FOUND.value());
        body.put("error", "Not Found");
        body.put("message", "Not Found");
        body.put("errors", exception.getMessage());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
}


