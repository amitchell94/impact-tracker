package com.codingnomads.impacttracker.Logic;

import com.codingnomads.impacttracker.Data.CommitmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
