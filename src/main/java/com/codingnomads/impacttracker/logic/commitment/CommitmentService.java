package com.codingnomads.impacttracker.logic.commitment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommitmentService {
    private CommitmentRepository commitmentRepository;

    @Autowired
    public CommitmentService(CommitmentRepository commitmentRepository){
        this.commitmentRepository=commitmentRepository;
    }

    public Commitment save(Commitment commitment){
        return commitmentRepository.save(commitment);
    }

    public List<Commitment> getCommitmentsFromUserId (int userId) {
        return commitmentRepository.getCommitmentsFromUserId(userId);
    }

}
