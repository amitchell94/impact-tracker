package com.codingnomads.impacttracker.logic.impact;

import com.codingnomads.impacttracker.data.MySqlImpactRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ImpactServiceTest {

    private static final int VALID_REDUCTION_ID = 1;
    ImpactRepository mockImpactRepository;
    ImpactService impactService;

    ImpactWithAverage impactWithAverage1;
    ImpactWithAverage impactWithAverage2;

    Impact impact1;
    Impact impact2;

    @Before
    public void setUp() throws Exception {

        impactWithAverage1 = new ImpactWithAverage();
        impactWithAverage1.setAveragePerDay(4);
        impactWithAverage2 = new ImpactWithAverage();
        impactWithAverage2.setAveragePerDay(5);

        impact1 = new Impact();
        impact1.setId(1);
        impact2 = new Impact();
        impact2.setId(2);

        mockImpactRepository = Mockito.mock(MySqlImpactRepository.class);
        impactService = new ImpactService(mockImpactRepository);

    }

    @Test
    public void whenGettingImpactsFromReductionId_shouldReturnImpacts() {
        when(mockImpactRepository.getImpactsWithAveragesFromReductionId(VALID_REDUCTION_ID)).thenReturn(Arrays.asList(impactWithAverage1, impactWithAverage2));

        List<ImpactWithAverage> result = impactService.getImpactsWithAveragesFromReductionId(VALID_REDUCTION_ID);

        verify(mockImpactRepository).getImpactsWithAveragesFromReductionId(VALID_REDUCTION_ID);
        assertThat(result).hasSize(2);
        assertThat(result).contains(impactWithAverage1, impactWithAverage2);
    }

    @Test
    public void whenGettingImpactsWithAveragesFromReductionId_shouldReturnImpactsWithAverage () {
        when(mockImpactRepository.getImpactsFromReductionId(VALID_REDUCTION_ID)).thenReturn(Arrays.asList(impact1, impact2));

        List<Impact> result = impactService.getImpactsFromReductionId(VALID_REDUCTION_ID);

        verify(mockImpactRepository).getImpactsFromReductionId(VALID_REDUCTION_ID);
        assertThat(result).hasSize(2);
        assertThat(result).contains(impact1, impact2);
    }
}