package com.bridgelabz.candidateservice.service;

import com.bridgelabz.candidateservice.dto.CandidateDTO;
import com.bridgelabz.candidateservice.dto.CombinedResponseDTO;
import com.bridgelabz.candidateservice.model.Candidate;

import java.util.List;

public interface ICandidateService {
    Object insertCandidate(CandidateDTO candidateDTO);

    List<CombinedResponseDTO> getAll();

    CombinedResponseDTO getByIdAllBQ(long id);
    Candidate getById(long candidateId);

    CombinedResponseDTO getByToken(String token);

    Candidate updateRecordByToken(String token, CandidateDTO candidateDTO);

    List<CombinedResponseDTO> hiredCandidate(String status);
    long count1(String status);

    long count();

    Candidate jobOfferMail(String token);

    Candidate jobOfferMail(long id);

    Candidate updateRecordById(long id, CandidateDTO candidateDTO);

    void deletebyId(long id);

    void deleteByToken(String token);
}
