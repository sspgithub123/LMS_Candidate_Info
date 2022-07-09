package com.bridgelabz.candidateservice.service;

import com.bridgelabz.candidateservice.dto.CandidateDTO;
import com.bridgelabz.candidateservice.dto.CombinedResponseDTO;
import com.bridgelabz.candidateservice.exception.CandidateException;
import com.bridgelabz.candidateservice.model.Candidate;
import com.bridgelabz.candidateservice.repository.CandidateRepository;
import com.bridgelabz.candidateservice.util.EmailSenderService;
import com.bridgelabz.candidateservice.util.TokenUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CandidateService implements ICandidateService {

    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private TokenUtility util;
    @Autowired
    private EmailSenderService mailService;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Object insertCandidate(CandidateDTO candidateDTO) {
        Candidate candidate = new Candidate(candidateDTO);
        candidateRepository.save(candidate);
        String token = util.createToken((int) candidate.getId());
        mailService.sendEmail(candidate.getEmail(), "Welcome " + candidate.getFirstName(),
                " Your candidate application submitted successfully!! . Please Click here to get data-> " +
                        "http://localhost:9012/candidateDetails/getByToken/" + token);
        return candidate;
    }


    @Override
    public List<CombinedResponseDTO> getAll() {
        List<Candidate> getCandidates = candidateRepository.findAll();
        List<CombinedResponseDTO> combinedResponseDTOList = new ArrayList<>();
        if (getCandidates.isEmpty()) {
            throw new CandidateException(HttpStatus.NOT_FOUND, "There is not data added yet!!");
        } else
            for (int i = 1; i <= candidateRepository.count(); i++) {
                CombinedResponseDTO combinedResponseDTO = new CombinedResponseDTO();
                //        get Candidate details first
                Object candidateInfo = restTemplate.getForObject("http://localhost:8083/candidateDetails/get/" + i, Object.class);
                combinedResponseDTO.setCandidate(candidateInfo);
                //        get Qualification details
                Object qualificationInfoList = restTemplate.getForObject("http://localhost:8084/candidateQualification/getby/" + i, Object.class);
                combinedResponseDTO.setQualificationInfoList(qualificationInfoList);
                //        get BankInfo details
                Object bankInfoList = restTemplate.getForObject("http://localhost:8082/bankInfo/get/" + i, Object.class);
                combinedResponseDTO.setBankInfoList(bankInfoList);
                combinedResponseDTOList.add(combinedResponseDTO);

            }
        return combinedResponseDTOList;
    }

    @Override
    public CombinedResponseDTO getByIdAllBQ(long id) {
        CombinedResponseDTO combinedResponseDTO = new CombinedResponseDTO();
//        get Candidate details first
        Optional candidate = candidateRepository.findById(id);
        if (candidate.isPresent()) {
            combinedResponseDTO.setCandidate(candidate);
            //        get Qualification details
            Object qualificationInfoList = restTemplate.getForObject("http://localhost:8084/candidateQualification/getby/" + id, Object.class);
            combinedResponseDTO.setQualificationInfoList(qualificationInfoList);
            System.out.println("Qualification details" + qualificationInfoList);
//        get BankInfo details
            Object bankInfoList = restTemplate.getForObject("http://localhost:8082/bankInfo/get/" + id, Object.class);
            combinedResponseDTO.setBankInfoList(bankInfoList);
            System.out.println("Bankinfo details" + bankInfoList);
            return combinedResponseDTO;
        } else
            throw new CandidateException(HttpStatus.NOT_FOUND, "Candidate with this Id not found!");
    }

    @Override
    public Candidate getById(long candidateId) {
        Optional<Candidate> candidate = candidateRepository.findById(candidateId);
        if (candidate.isPresent()) {
            return candidate.get();
        } else
            throw new CandidateException(HttpStatus.NOT_FOUND, "This Id is not found! ");
    }


    @Override
    public CombinedResponseDTO getByToken(String token) {
        long id = util.decodeToken(token);
        CombinedResponseDTO combinedResponseDTO = new CombinedResponseDTO();
        //        get Candidate details first
        Optional<Candidate> candidate = candidateRepository.findById(id);
        if (candidate.isPresent()) {
            combinedResponseDTO.setCandidate(candidate);
            //        get Qualification details
            Object qualificationInfoList = restTemplate.getForObject("http://localhost:8084/candidateQualification/getby/" + id, Object.class);
            combinedResponseDTO.setQualificationInfoList(qualificationInfoList);
            //        get BankInfo details
            Object bankInfoList = restTemplate.getForObject("http://localhost:8082/bankInfo/get/" + id, Object.class);
            combinedResponseDTO.setBankInfoList(bankInfoList);
            return combinedResponseDTO;
        } else {
            throw new CandidateException("Exception with id" + id + "does not exist!!");
        }
    }

    @Override
    public Candidate updateRecordByToken(String token, CandidateDTO candidateDTO) {
        long id = util.decodeToken(token);
        Optional<Candidate> candidate = candidateRepository.findById(id);
        if (candidate.isPresent()) {
            Candidate newCandidate = new Candidate(id, candidateDTO);
            candidateRepository.save(newCandidate);
            mailService.sendEmail(newCandidate.getEmail(), "Welcome " + newCandidate.getFirstName(),
                    " Your candidate application Updated successfully!! . Please Click here to get data-> " +
                            "http://localhost:8083/candidateDetails/getByToken/" + token);
            return newCandidate;
        } else {
            throw new CandidateException(HttpStatus.NOT_FOUND, "Candidate not found by this token");
        }
    }

    @Override
    public List<CombinedResponseDTO> hiredCandidate(String status) {
        List<Candidate> candidate = candidateRepository.findCandidateByStatus(status);
        List<CombinedResponseDTO> combinedResponseDTOList = new ArrayList<>();
        if (candidate.isEmpty()) {
            throw new CandidateException(HttpStatus.NOT_FOUND, "There are no candidate got hired yet!!");
        }
        //1234
        List<Long> ids = new ArrayList<>();
        for (Candidate candidateId : candidate) {
            long id = candidateId.getId();
            ids.add(id);
        }
        for (long id : ids) {
            CombinedResponseDTO combinedResponseDTO = new CombinedResponseDTO();
            //        get Candidate details first
            Object candidateInfo = restTemplate.getForObject("http://localhost:9012/candidateDetails/get/"
                    + id, Object.class);
            combinedResponseDTO.setCandidate(candidateInfo);
            //        get Qualification details
            Object qualificationInfoList = restTemplate.getForObject("http://localhost:9011/qualification/getby/"
                    + id, Object.class);
            combinedResponseDTO.setQualificationInfoList(qualificationInfoList);
            //        get BankInfo details
            Object bankInfoList = restTemplate.getForObject("http://localhost:9010/bankinfo/get/" + id, Object.class);
            combinedResponseDTO.setBankInfoList(bankInfoList);
            combinedResponseDTOList.add(combinedResponseDTO);

        }
        return combinedResponseDTOList;
    }


    @Override
    public long count1(String status) {
        return candidateRepository.countByStatusEquals(status);
    }

    @Override
    public long count() {
        return candidateRepository.count();
    }


    @Override
    public Candidate jobOfferMail(String token) {
        long id = util.decodeToken(token);
        Optional<Candidate> hiredCandidate = candidateRepository.findById(id);
        if (hiredCandidate.isPresent()) {
            mailService.sendEmail(hiredCandidate.get().getEmail(), "Job Offer", " Hi " + hiredCandidate.get().getFirstName()
                    + "\n You have been Shortlisted for the software engineer position. Congratulations!");

        }
        return hiredCandidate.get();
    }

    @Override
    public Candidate jobOfferMail(long id) {
        Optional<Candidate> hiredCandidate = candidateRepository.findById(id);
        if (hiredCandidate.isPresent()) {
            mailService.sendEmail(hiredCandidate.get().getEmail(), "Job Offer", " Hi " + hiredCandidate.get().getFirstName()
                    + "\n You have been Shortlisted for the software engineer position. Congratulations!");

        }
        return hiredCandidate.get();
    }

    @Override
    public Candidate updateRecordById(long id, CandidateDTO candidateDTO) {
        Optional<Candidate> candidate = candidateRepository.findById(id);
        if (candidate.isPresent()) {
            Candidate newCandidate = new Candidate(id, candidateDTO);
            candidateRepository.save(newCandidate);
            return newCandidate;
        } else {
            throw new CandidateException(HttpStatus.NOT_FOUND, "Candidate not found by this Id");
        }
    }

    @Override
    public void deletebyId(long id) {
        Optional<Candidate> candidate = candidateRepository.findById(id);
        if (candidate.isPresent()) {
            //        get Qualification details
            Object qualificationInfoList = restTemplate.getForObject("http://localhost:8084/candidateQualification/deleteBy/" + id, Object.class);
            //        get BankInfo details
            Object bankInfoList = restTemplate.getForObject("http://localhost:8082/bankInfo/delete/" + id, Object.class);
            candidateRepository.deleteById(id);
        } else {
            throw new CandidateException("Exception with id" + id + "does not exist!!");
        }
    }


    @Override
    public void deleteByToken(String token) {
        long id = util.decodeToken(token);
        //        get Candidate details first
        Optional<Candidate> candidate = candidateRepository.findById(id);
        if (candidate.isPresent()) {
            //        get Qualification details
            Object qualificationInfoList = restTemplate.getForObject("http://localhost:8084/candidateQualification/deleteBy/" + id, Object.class);
            //        get BankInfo details
            Object bankInfoList = restTemplate.getForObject("http://localhost:8082/bankInfo/delete/" + id, Object.class);
            candidateRepository.deleteById(id);
        } else {
            throw new CandidateException("Exception with id" + id + "does not exist!!");
        }
    }
}