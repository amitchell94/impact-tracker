package com.codingnomads.impacttracker.logic.commitment;

import com.codingnomads.impacttracker.model.Commitment;
import com.codingnomads.impacttracker.model.CommitmentWithReduction;
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

    public List<CommitmentWithReduction> getCommitmentsWithReductionsFromUserId(int userId) {
        return commitmentRepository.getCommitmentsWithReductionsFromUserId(userId);
    }

    public Commitment updateCommitmentById(int id, Commitment commitment){
        return commitmentRepository.updateCommitmentById(id, commitment);
    }

    public void deleteCommitmentById(int id){
        commitmentRepository.deleteCommitmentById(id);
    }


    public Commitment getCommitmentById(Integer id) {
        return commitmentRepository.getCommitmentById(id);
    }
}
