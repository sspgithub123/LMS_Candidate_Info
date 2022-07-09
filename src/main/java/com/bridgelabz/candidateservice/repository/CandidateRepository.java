package com.bridgelabz.candidateservice.repository;

import com.bridgelabz.candidateservice.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {

    List<Candidate> findCandidateByStatus(String status);

    long countByStatusEquals(String status);
}
