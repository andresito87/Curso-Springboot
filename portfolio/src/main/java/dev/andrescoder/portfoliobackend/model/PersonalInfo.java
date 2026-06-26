package dev.andrescoder.portfoliobackend.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonalInfo {
    private Long id; // No lo validamos porque esto no lo introduce el usuario

    @NotBlank(message = "First name can not be empty")
    private String firstName;
    @NotBlank(message = "Last name can not be empty")
    private String lastName;
    @NotBlank(message = "Title can not be empty")
    private String title;
    @Email(message = "Email is not valid")
    private String email;
    @NotBlank(message = "Phone can not be empty")
    private String phone;
    @NotBlank(message = "Profile description can not be empty")
    private String profileDescription;
    @NotBlank(message = "Profile image URL can not be empty")
    private String profileImageUrl;
    @Min(value = 0, message = "Years of experience must be a positive number")
    private Integer yearsOfExperience;
    @URL(message = "LinkedIn url must be valid")
    private String linkedinUrl;
    @URL(message = "GitHub url must be valid")
    private String githubUrl;
}
