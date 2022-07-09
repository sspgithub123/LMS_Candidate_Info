package com.bridgelabz.candidateservice.dto;

import com.bridgelabz.candidateservice.model.BankInfo;
import com.bridgelabz.candidateservice.model.Candidate;
import com.bridgelabz.candidateservice.model.QualificationInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public @Data class CombinedResponseDTO {
    private Object candidate;
    private Object qualificationInfoList;
    private Object bankInfoList;
}
