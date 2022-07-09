package com.bridgelabz.candidateservice.model;

import com.bridgelabz.candidateservice.dto.BankInfoDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@AllArgsConstructor
public @Data class BankInfo {
    @Id
    @GeneratedValue
    public long id;
    public String bankName;
    public String bankAccountNumber;
    public String bankBranch;
    public String ifscCode;
    public long candidateId;

    public BankInfo() {
        super();
    }

    public BankInfo(BankInfoDto bankInfoDto) {
        this.bankName = bankInfoDto.getBankName();
        this.bankAccountNumber = bankInfoDto.getBankAccountNumber();
        this.bankBranch = bankInfoDto.getBankBranch();
        this.ifscCode = bankInfoDto.getIfscCode();
        this.candidateId=bankInfoDto.getCandidateId();
    }

    public BankInfo(long id, BankInfoDto bankInfoDto) {
        this.id = id;
        this.bankName = bankInfoDto.getBankName();
        this.bankAccountNumber = bankInfoDto.getBankAccountNumber();
        this.bankBranch = bankInfoDto.getBankBranch();
        this.ifscCode = bankInfoDto.getIfscCode();
        this.candidateId=bankInfoDto.getCandidateId();
    }
}
