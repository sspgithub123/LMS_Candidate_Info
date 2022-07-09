package com.bridgelabz.candidateservice.model;

import com.bridgelabz.candidateservice.dto.QualificationDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
public @Data class QualificationInfo {
    @Id
    @GeneratedValue
    public long id;
    public String collegeName;

    public String higherEducation;

    public String percentage;

    public String yearOfPassing;

    public String course;

    public String document;

    public long candidateId;

    public QualificationInfo(QualificationDto qualificationDto) {
        super();
        this.collegeName = qualificationDto.getCollegeName();
        this.higherEducation = qualificationDto.getHigherEducation();
        this.percentage = qualificationDto.getPercentage();
        this.yearOfPassing = qualificationDto.getYearOfPassing();
        this.course = qualificationDto.getCourse();
        this.document = qualificationDto.getDocument();
        this.candidateId=qualificationDto.getCandidateId();
    }

    public QualificationInfo(long id, QualificationDto qualificationDto) {
        this.id=id;
        this.collegeName = qualificationDto.getCollegeName();
        this.higherEducation = qualificationDto.getHigherEducation();
        this.percentage = qualificationDto.getPercentage();
        this.yearOfPassing = qualificationDto.getYearOfPassing();
        this.course = qualificationDto.getCourse();
        this.document = qualificationDto.getDocument();
        this.candidateId=qualificationDto.getCandidateId();
    }

}