package com.codingnomads.impacttracker.presentation.api;

import com.codingnomads.impacttracker.logic.JWT.InvalidTokenException;
import com.codingnomads.impacttracker.logic.JWT.OurTokenService;
import com.codingnomads.impacttracker.logic.commitment.CommitmentService;
import com.codingnomads.impacttracker.model.Commitment;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static com.codingnomads.impacttracker.presentation.api.ObjectToJsonHelper.asJsonString;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = CommitmentApiController.class, secure = false)
public class CommitmentApiControllerTest {

    private static final String INVALID_TOKEN = "11111";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CommitmentService commitmentService;

    @MockBean
    private OurTokenService ourTokenService;

    @Test
    public void whenGettingAllCommitments_withValidToken_thenReturnJsonArray() throws Exception {
        Commitment commitment1 = new Commitment();
        commitment1.setUserId(1);
        commitment1.setReductionId(2);
        List<Commitment> commitmentList = Arrays.asList(commitment1);

        given(commitmentService.getCommitmentsFromUserId(1)).willReturn(commitmentList);

        mvc.perform(get("/api/commitments/1/?token=1369732878")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].reductionId", CoreMatchers.is(commitment1.getReductionId())));
    }

    @Test
    public void whenGettingAllCommitments_withInvalidToken_thenReturnUnauthorizedResponse() throws Exception {
        InvalidTokenException invalidTokenException = new InvalidTokenException(" ");
        Mockito.doThrow(invalidTokenException).when(ourTokenService).validateTokenByValue(INVALID_TOKEN);

        mvc.perform(get("/api/commitments/1?token=" + INVALID_TOKEN)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnauthorized())
                .andExpect(status().reason(containsString("Invalid Token")));
    }

    @Test
    public void whenAddingCommitmentWithValidToken_shouldReturnJsonArray() throws Exception {
        Commitment commitment2 = new Commitment();
        commitment2.setUserId(11);
        commitment2.setReductionId(2);
        commitment2.setAmountToReduceBy(2);

        when(commitmentService.save(commitment2)).thenReturn(commitment2);
        mvc.perform(post("/api/commitments/addcommitment?token=142810290")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(commitment2)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(commitment2)));
    }

    @Test
    public void whenUpdatingCommitmentByIdWithValidToken_shouldReturnJsonArray() throws Exception {
        Commitment commitment3 = new Commitment();
        commitment3.setId(1);
        commitment3.setUserId(11);
        commitment3.setReductionId(2);
        commitment3.setAmountToReduceBy(2);

        Commitment commitment3updatedVersion = new Commitment();
        commitment3.setUserId(11);
        commitment3.setReductionId(1);
        commitment3.setAmountToReduceBy(1);

        when(commitmentService.updateCommitmentById(1, commitment3updatedVersion)).thenReturn(commitment3);
        mvc.perform(put("/api/commitments/updatecommitment/1?token=142810290")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(commitment3updatedVersion)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(commitment3)));
    }

    @Test
    public void whenDeletingCommitmentById_shouldReturnVoid() throws Exception{

        mvc.perform(delete("/api/commitments/deletecommitment/4?token=142810290")
        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }


}












