package com.codingnomads.impacttracker.logic.impact;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImpactRepository {
    List<Impact> getImpactsFromReductionId(int reductionId);

    List<ImpactWithAverage> getImpactsWithAveragesFromReductionId(int reductionId);
}
