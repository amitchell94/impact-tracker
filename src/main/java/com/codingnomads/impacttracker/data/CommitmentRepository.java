package com.codingnomads.impacttracker.data;

import com.codingnomads.impacttracker.logic.Commitment;
import org.springframework.stereotype.Repository;

@Repository
public interface CommitmentRepository {
    Commitment save(Commitment commitment);
}
