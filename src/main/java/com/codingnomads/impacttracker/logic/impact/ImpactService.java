package com.codingnomads.impacttracker.logic.impact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImpactService {

    private ImpactRepository impactRepository;

    @Autowired
    public ImpactService(ImpactRepository impactRepository) {
        this.impactRepository = impactRepository;
    }

    public List<Impact> getImpactsFromReductionId(int reductionId) {
        return impactRepository.getImpactsFromReductionId(reductionId);
    }

    public List<ImpactWithAverage> getImpactsWithAveragesFromReductionId (int reductionId) {
        return impactRepository.getImpactsWithAveragesFromReductionId(reductionId);
    }
}
