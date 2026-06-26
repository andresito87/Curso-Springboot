package dev.andrescoder.portfoliobackend.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDto {

    private Long id;

    @NotBlank(message = "Title can not be empty")
    @Size(min = 2, max = 255, message = "Title must be between 2 and 255 characters")
    private String title;

    @NotBlank(message = "Description can not be empty")
    @Size(max = 10, message = "Description can not exceed 10 characters")
    private String description;

    @URL(message = "Image url must be valid")
    private String imageUrl;

    @URL(message = "Project url must be valid")
    private String projectUrl;

    @NotNull(message = "Personal info id can not be null")
    @Min(value = 1, message = "Personal info id must be a positive number")
    private Long personalInfoId;
}
