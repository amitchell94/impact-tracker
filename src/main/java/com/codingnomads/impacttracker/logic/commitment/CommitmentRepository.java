package com.codingnomads.impacttracker.logic.commitment;

import com.codingnomads.impacttracker.model.Commitment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommitmentRepository {
    Commitment save(Commitment commitment);


    List<Commitment> getCommitmentsFromUserId(int userId);

    Commitment updateCommitmentById(int id, Commitment commitment);

    void deleteCommitmentById(int id);

    Commitment getCommitmentById(int id);
}
