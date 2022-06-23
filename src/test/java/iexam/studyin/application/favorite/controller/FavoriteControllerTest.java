package iexam.studyin.application.favorite.controller;

import iexam.studyin.application.favorite.service.FavoriteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
class FavoriteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FavoriteService favoriteService;

    private String dummy = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhYmNAYWJjLmNvbSIsImV4cCI6MTY1NTkzOTQ4OCwiaWF0IjoxNjU1OTAzNDg4fQ.rzOsZHg_bYAczZsvPu1KUSrzbtVP6JdDMHFb6T0pwRU";

    @Test
    public void likeExamNoPrincpalTest() throws Exception {
        this.mockMvc.perform(post("/exam/{id}/favorite", 1L)
                .header("Authorization", dummy)).andExpect(status().isBadRequest());
    }

}