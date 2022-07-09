package com.bridgelabz.candidateservice.model;

import com.bridgelabz.candidateservice.dto.BankInfoDto;
import com.bridgelabz.candidateservice.dto.CandidateDTO;
import com.bridgelabz.candidateservice.dto.QualificationDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Transient;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "candidateInformation")
public @Data class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String firstName;
    private String lastName;
    private String status;
    private String email;
    private String phonenumber;
    private String city;

    public Candidate(CandidateDTO candidateDTO) {
        super();
        this.firstName = candidateDTO.getFirstName();
        this.lastName = candidateDTO.getLastName();
        this.status = candidateDTO.getStatus();
        this.email = candidateDTO.getEmail();
        this.phonenumber = candidateDTO.getPhonenumber();
        this.city = candidateDTO.getCity();
    }

    public Candidate() {
    }

    public Candidate(long id, CandidateDTO candidateDTO) {
        this.id = id;
        this.firstName = candidateDTO.getFirstName();
        this.lastName = candidateDTO.getLastName();
        this.status = candidateDTO.getStatus();
        this.email = candidateDTO.getEmail();
        this.phonenumber = candidateDTO.getPhonenumber();
        this.city = candidateDTO.getCity();
    }
}