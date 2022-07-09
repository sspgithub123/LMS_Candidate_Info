package com.bridgelabz.candidateservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
public @Data class BankInfoDto {

    @NotBlank(message = "Enter your bankName")
    public String bankName;
    @NotBlank(message = "Enter your bank Account.no")
    public String bankAccountNumber;
    @NotBlank(message = "Enter your bank branch name")
    public String bankBranch;
    @NotBlank(message = "Enter ifscCode of the bank")
    public String ifscCode;
    public long candidateId;
}
