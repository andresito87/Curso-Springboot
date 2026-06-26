package dev.andrescoder.portfoliobackend.exception;

import lombok.Getter;
import org.springframework.validation.BindingResult;

@Getter
public class ValidationException extends RuntimeException {

    private final BindingResult bindingResult;

    public ValidationException(BindingResult bindingResult) {
        super("Validation errors found: " + bindingResult.getAllErrors().toArray().length + " error(s). " + bindingResult.getFieldErrors());
        this.bindingResult = bindingResult;
    }
}
