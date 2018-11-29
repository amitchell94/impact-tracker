package com.codingnomads.impacttracker.logic;

import com.codingnomads.impacttracker.data.MySqlCommitmentRepository;
import com.codingnomads.impacttracker.logic.commitment.Commitment;
import com.codingnomads.impacttracker.logic.commitment.CommitmentService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CommitmentServiceTest {

    private static final int VALID_USER_ID = 1;
    private MySqlCommitmentRepository mySqlCommitmentRepositoryMock;
    private CommitmentService commitmentService;
    private Commitment commitment1;
    private Commitment commitment2;
    private Commitment commitmentFromRepository;

    @Before
    public void setUp() {
        mySqlCommitmentRepositoryMock = Mockito.mock(MySqlCommitmentRepository.class);
        commitmentService = new CommitmentService(mySqlCommitmentRepositoryMock);
        commitment1 = new Commitment();
        commitment1.setId(1);
        commitment2 = new Commitment();
        commitment2.setId(2);

        commitmentFromRepository = new Commitment();
    }

    @Test
    public void whenSavingCommitment_shouldSaveCommitment() {
        when(mySqlCommitmentRepositoryMock.save(commitment1)).thenReturn(commitmentFromRepository);

        Commitment savedCommitment = commitmentService.save(commitment1);

        verify(mySqlCommitmentRepositoryMock).save(commitment1);
        assertThat(savedCommitment).isEqualTo(commitmentFromRepository);
    }

    @Test
    public void whenGettingCommitmentFromUserId_shouldReturnListOfCommitments() {
        when(mySqlCommitmentRepositoryMock.getCommitmentsFromUserId(VALID_USER_ID)).thenReturn(Arrays.asList(commitment1,commitment2));

        List<Commitment> commitmentList = commitmentService.getCommitmentsFromUserId(VALID_USER_ID);

        verify(mySqlCommitmentRepositoryMock).getCommitmentsFromUserId(VALID_USER_ID);
        assertThat(commitmentList).hasSize(2);
        assertThat(commitmentList).contains(commitment1,commitment2);
    }

}