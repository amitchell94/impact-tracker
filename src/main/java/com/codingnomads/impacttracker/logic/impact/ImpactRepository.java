package com.codingnomads.impacttracker.logic.impact;

import com.codingnomads.impacttracker.model.Impact;
import com.codingnomads.impacttracker.model.ImpactWithAverage;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImpactRepository {
    List<Impact> getImpactsFromReductionId(int reductionId);

    List<ImpactWithAverage> getImpactsWithAveragesFromReductionId(int reductionId);
}
