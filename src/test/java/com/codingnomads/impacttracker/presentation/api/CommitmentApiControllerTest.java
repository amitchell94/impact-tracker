package com.codingnomads.impacttracker.presentation.api;

import com.codingnomads.impacttracker.logic.JWT.AuthenticationService;
import com.codingnomads.impacttracker.logic.JWT.InvalidTokenException;
import com.codingnomads.impacttracker.logic.commitment.CommitmentService;
import com.codingnomads.impacttracker.model.Commitment;
import com.codingnomads.impacttracker.model.CommitmentWithReduction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static com.codingnomads.impacttracker.presentation.api.ObjectToJsonHelper.asJsonString;
import static org.hamcrest.CoreMatchers.containsString;
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
    private AuthenticationService authenticationService;

    @Test
    public void whenGettingAllCommitments_withValidToken_thenReturnJsonArray() throws Exception {
        CommitmentWithReduction commitment1 = new CommitmentWithReduction();
        commitment1.setUserId(1);
        commitment1.setReductionId(2);
        List<CommitmentWithReduction> commitmentList = Arrays.asList(commitment1);

        given(commitmentService.getCommitmentsWithReductionsFromUserId(0)).willReturn(commitmentList);

        mvc.perform(get("/api/commitments/?token=eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI4In0.Uv5gLkpibODJxW3I1oj2JkoxDv2gYO2-1MaOaKoarmk")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(commitmentList)));
    }

    @Test
    public void whenGettingAllCommitments_withInvalidToken_thenReturnUnauthorizedResponse() throws Exception {
        InvalidTokenException invalidTokenException = new InvalidTokenException(" ");
        Mockito.doThrow(invalidTokenException).when(authenticationService).validateToken(INVALID_TOKEN);

        mvc.perform(get("/api/commitments/?token=" + INVALID_TOKEN)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnauthorized())
                .andExpect(status().reason(containsString("Invalid Token")));
    }

 //   @Test
 //   public void whenAddingCommitmentWithValidToken_shouldReturnJsonArray() throws Exception {
 //       Commitment commitment2 = new Commitment();
 //       //commitment2.setUserId(11);
 //       commitment2.setReductionId(2);
 //       commitment2.setAmountToReduceBy(2);
//
 //       when(commitmentService.save(commitment2)).thenReturn(commitment2);
 //       mvc.perform(post("/api/commitments/addcommitment?token=142810290")
 //               .contentType(MediaType.APPLICATION_JSON)
 //               .content(asJsonString(commitment2)))
 //               .andDo(print())
 //               .andExpect(status().isOk())
 //               .andExpect(content().json(asJsonString(commitment2)));
   // }

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
        Integer commitmentId = 2;
        mvc.perform(delete("/api/commitments/deletecommitment/"+ commitmentId+"?token=142810290")
        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());

    }


}












