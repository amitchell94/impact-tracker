package com.codingnomads.impacttracker.logic;

import com.codingnomads.impacttracker.data.MySqlReductionRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class ReductionServiceTest {

    private ReductionService reductionService;
    private MySqlReductionRepository mySqlReductionRepositoryMock;
    private Reduction reduction1;
    private Reduction reduction2;

    @Before
    public void setUp() throws Exception {
        mySqlReductionRepositoryMock = Mockito.mock(MySqlReductionRepository.class);
        reductionService =new ReductionService(mySqlReductionRepositoryMock);

        reduction1 = new Reduction();
        reduction1.setId(1);

        reduction2 = new Reduction();
        reduction2.setId(2);
    }

    @Test
    public void whenGettingAllReductions_shouldRetunListOfReductions (){

        when(mySqlReductionRepositoryMock.getAllReductions()).thenReturn(Arrays.asList(reduction1, reduction2));

        List<Reduction> output = reductionService.getAllReductions();

        assertThat(output).contains(reduction1,reduction2);
        assertThat(output).hasSize(2);
    }

}