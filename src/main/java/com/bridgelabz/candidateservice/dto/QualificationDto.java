package com.bridgelabz.candidateservice.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public @Data class QualificationDto {

    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$", message = "College firstName is Not valid. first letter should be in upper case")
    public String collegeName;
    @NotBlank(message = "Enter your higher education name")
    public String higherEducation;
    @NotBlank(message = "Enter your percentage")
    public String percentage;
    @NotBlank(message = "Enter your yearOfPassing")
    public String yearOfPassing;
    @NotBlank(message = "Enter your course")
    public String course;
    @NotBlank(message = "Upload your documents")
    public String document;
    public long candidateId;
}
