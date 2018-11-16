package com.codingnomads.impacttracker.Logic;

import com.codingnomads.impacttracker.Data.MySqlCommitmentRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CommitmentServiceTest {

    private MySqlCommitmentRepository mySqlCommitmentRepositoryMock;
    private CommitmentService commitmentService;
    private Commitment commitment1;
    private Commitment commitmentFromRepository;

    @Before
    public void setUp() {
        mySqlCommitmentRepositoryMock = Mockito.mock(MySqlCommitmentRepository.class);
        commitmentService = new CommitmentService(mySqlCommitmentRepositoryMock);
        commitment1 = new Commitment();

        commitmentFromRepository = new Commitment();
    }

    @Test
    public void whenSavingCommitment_shouldSaveCommitment() {
        when(mySqlCommitmentRepositoryMock.save(commitment1)).thenReturn(commitmentFromRepository);

        Commitment savedCommitment = commitmentService.save(commitment1);

        verify(mySqlCommitmentRepositoryMock).save(commitment1);
        assertThat(savedCommitment).isEqualTo(commitmentFromRepository);
    }

}