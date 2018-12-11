package com.codingnomads.impacttracker.logic;

import com.codingnomads.impacttracker.model.Commitment;
import com.codingnomads.impacttracker.logic.commitment.CommitmentService;
import com.codingnomads.impacttracker.logic.impact.ImpactService;
import com.codingnomads.impacttracker.model.ImpactWithAverage;
import com.codingnomads.impacttracker.model.Statistic;
import com.codingnomads.impacttracker.logic.statistic.StatisticsService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Arrays;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.when;

public class StatisticsServiceTest {

    private static final Double UNROUNDED_VALUE = 2.3434;
    private static final double VALUE_ROUNDED_TO_TWO_DP = 2.34;
    private static final int VALID_USER_ID = 1;
    private static final Integer VALID_REDUCTION_ID = 1;
    private static final Integer SECOND_VALID_REDUCTION_ID = 2;
    private static final LocalDate VALID_START_DATE = LocalDate.of(2000, 1, 1);
    private static final LocalDate VALID_END_DATE = LocalDate.of(2000, 1, 2);
    private static final double VALID_IMPACT_PER_UNIT = 10;
    private static final String VALID_IMPACT_TYPE = "co2 emissions";
    private static final String SECOND_VALID_IMPACT_TYPE = "water use";
    private static final long SECOND_VALID_IMPACT_PER_UNIT = 20;
    private static final int VALID_AVERAGE_PER_DAY = 1;
    private static final int SECOND_VALID_AVERAGE_PER_DAY = 2;

    private Commitment commitment1;
    private Commitment commitment2;

    private ImpactWithAverage impact1;
    private ImpactWithAverage impact2;

    private StatisticsService statisticsService;
    private ImpactService impactService;
    private CommitmentService commitmentService;

    @Before
    public void setUp() {

        commitment1 = new Commitment();
        commitment1.setReductionId(VALID_REDUCTION_ID);
        commitment1.setStartDate(VALID_START_DATE);
        commitment1.setEndDate(VALID_END_DATE);

        commitment2 = new Commitment();
        commitment2.setReductionId(SECOND_VALID_REDUCTION_ID);
        commitment2.setStartDate(VALID_START_DATE);
        commitment2.setEndDate(VALID_END_DATE);

        impact1 = new ImpactWithAverage();
        impact1.getImpact().setImpactType(VALID_IMPACT_TYPE);
        impact1.getImpact().setImpactPerUnit(VALID_IMPACT_PER_UNIT);
        impact1.setAveragePerDay(VALID_AVERAGE_PER_DAY);
        impact2 = new ImpactWithAverage();
        impact2.getImpact().setImpactType(SECOND_VALID_IMPACT_TYPE);
        impact2.getImpact().setImpactPerUnit(SECOND_VALID_IMPACT_PER_UNIT);
        impact2.setAveragePerDay(SECOND_VALID_AVERAGE_PER_DAY);

        impactService = Mockito.mock(ImpactService.class);
        commitmentService = Mockito.mock(CommitmentService.class);

        statisticsService = new StatisticsService(impactService,commitmentService);
    }

   @Test
   public void whenGettingTotalImpact_shouldCalculateTotalImpact () {

       when(commitmentService.getCommitmentsFromUserId(VALID_USER_ID)).thenReturn(Arrays.asList(commitment1, commitment2));
       when(impactService.getImpactsWithAveragesFromReductionId(VALID_REDUCTION_ID)).thenReturn(Arrays.asList(impact1));
       when(impactService.getImpactsWithAveragesFromReductionId(SECOND_VALID_REDUCTION_ID)).thenReturn(Arrays.asList(impact2));

       Statistic totalImpact = statisticsService.getImpactForTimePeriod(VALID_USER_ID,0);

       assertThat(totalImpact.getTonsOfCo2()).isEqualTo(VALID_IMPACT_PER_UNIT*VALID_AVERAGE_PER_DAY);
       assertThat(totalImpact.getGallonsOfWater()).isEqualTo(SECOND_VALID_IMPACT_PER_UNIT*SECOND_VALID_AVERAGE_PER_DAY);

   }


    @Test
    public void whenRoundingToTwoDP_shouldReturnRoundedNumber() {
        Double methodOutput = statisticsService.roundToTwoDP(UNROUNDED_VALUE);

        assertThat(methodOutput).isEqualTo(VALUE_ROUNDED_TO_TWO_DP);
    }

    @Test
    public void whenRoundingANullValue_shouldReturnNull(){
        Double methodOutput = statisticsService.roundToTwoDP(null);

        assertThat(methodOutput).isEqualTo(null);
    }
}