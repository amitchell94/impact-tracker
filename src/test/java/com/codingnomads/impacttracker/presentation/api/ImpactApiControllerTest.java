package com.codingnomads.impacttracker.presentation.api;

import com.codingnomads.impacttracker.logic.JWT.AuthenticationService;
import com.codingnomads.impacttracker.logic.statistic.StatisticsService;
import com.codingnomads.impacttracker.model.Statistic;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static com.codingnomads.impacttracker.presentation.api.ObjectToJsonHelper.asJsonString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ImpactApiController.class, secure = false)
public class ImpactApiControllerTest {


    private static final String INVALID_TOKEN = "11111";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private StatisticsService statisticsService;

    @MockBean
    private AuthenticationService authenticationService;

    @Test
    public void whenGettingTotalImpactForUserWithValidToken_returnTotalImpactForUser() throws Exception {
        Statistic statistic1 = new Statistic();
        statistic1.getGallonsOfWater().setTotalImpact(5);
        statistic1.getTonsOfCo2().setTotalImpact(1);

        given(statisticsService.getImpactForTimePeriod(0, 0)).willReturn(statistic1);

        mvc.perform(get("/api/impact/total/?token=1591618460")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(statistic1)));
    }

    @Test
    public void whenGettingWeekImpactForUserWithValidToken_returnWeekImpactForUser() throws Exception {
        Statistic statistic1 = new Statistic();
        statistic1.getGallonsOfWater().setTotalImpact(5);
        statistic1.getTonsOfCo2().setTotalImpact(1);

        given(statisticsService.getImpactForTimePeriod(0, 7)).willReturn(statistic1);

        mvc.perform(get("/api/impact/week/?token=1591618460")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(statistic1)));
    }

    @Test
    public void whenGettingMonthImpactForUserWithValidToken_returnMonthImpactForUser() throws Exception {
        Statistic statistic1 = new Statistic();
        statistic1.getGallonsOfWater().setTotalImpact(5);
        statistic1.getTonsOfCo2().setTotalImpact(1);

        given(statisticsService.getImpactForTimePeriod(0, 30)).willReturn(statistic1);

        mvc.perform(get("/api/impact/month/?token=1591618460")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(statistic1)));
    }

    @Test
    public void whenGettingYearImpactForUserWithValidToken_returnYearImpactForUser() throws Exception {
        Statistic statistic1 = new Statistic();
        statistic1.getGallonsOfWater().setTotalImpact(5);
        statistic1.getTonsOfCo2().setTotalImpact(1);

        given(statisticsService.getImpactForTimePeriod(0, 365)).willReturn(statistic1);

        mvc.perform(get("/api/impact/year/?token=1591618460")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(statistic1)));
    }
}