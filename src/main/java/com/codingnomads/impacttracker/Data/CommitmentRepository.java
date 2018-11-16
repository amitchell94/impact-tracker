package com.codingnomads.impacttracker.Data;

import com.codingnomads.impacttracker.Logic.Commitment;
import org.springframework.stereotype.Repository;

@Repository
public interface CommitmentRepository {
    Commitment save(Commitment commitment);
}
