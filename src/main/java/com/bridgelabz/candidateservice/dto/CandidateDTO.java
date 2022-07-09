package com.bridgelabz.candidateservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class CandidateDTO {

    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$", message = "Candidate firstName is Not valid. first letter should be in upper case")
    private String firstName;
    @Pattern(regexp = "[A-Za-z\\s]+", message = " User lastname is invalid!")
    private String lastName;
    @NotEmpty(message = "Status should be not blank")
    private String status;
    @Email(message = "Insert valid email")
    private String email;
    @NotEmpty(message = "Phone Number is required")
    private String phonenumber;
    @NotEmpty(message = "Enter your city")
    private String city;
}