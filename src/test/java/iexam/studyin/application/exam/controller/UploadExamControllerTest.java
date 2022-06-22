package iexam.studyin.application.exam.controller;

import iexam.studyin.application.exam.controller.dto.OneExamDto;
import iexam.studyin.application.exam.service.ExamService;
import iexam.studyin.application.favorite.controller.dto.FavoriteExamDto;
import iexam.studyin.core.config.auth.PrincipalDetails;
import iexam.studyin.core.config.auth.PrincipalDetailsService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
class UploadExamControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExamService examService;

    @Autowired
    PrincipalDetailsService principalDetailsService;

    private String dummy = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhYmNAYWJjLmNvbSIsImV4cCI6MTY1NTkzOTQ4OCwiaWF0IjoxNjU1OTAzNDg4fQ.rzOsZHg_bYAczZsvPu1KUSrzbtVP6JdDMHFb6T0pwRU";

    @Test
    public void uploadTest() throws Exception {
        //given
        doNothing().when(examService).upload(any(), any());

        //when then
        this.mockMvc.perform(post("/api/exam/upload")
                        .header("Authorization", dummy))
                .andExpect(status().isOk());
    }

    @Test
    public void lookUpOneTest() throws Exception {
        //given
        OneExamDto build = getOneExamDto();
        doReturn(build).when(examService).findByExamId(any());

        //when
        this.mockMvc.perform(get("/api/exam/{id}", 1L)
                .header("Authorization", dummy)
        ).andExpect(status().isOk());
    }

    @Test
    public void myExamsAllNoMemberTest() throws Exception {
        //given
        List<FavoriteExamDto> favoriteExamDtos = getFavoriteExamDtos();
        doReturn(favoriteExamDtos).when(examService).findMyExams(any());

        //when
        this.mockMvc.perform(get("/api/myExam")
                .header("Authorization", dummy))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void myExamsAllTest() throws Exception {
        //given
        List<FavoriteExamDto> favoriteExamDtos = getFavoriteExamDtos();
        doReturn(favoriteExamDtos).when(examService).findMyExams(any());
        PrincipalDetails principalDetails = (PrincipalDetails) principalDetailsService.loadUserByUsername("abc@abc");
        String username = principalDetails.getUsername();

        //when
        this.mockMvc.perform(get("/api/myExam")
                        .header("Authorization", dummy))
                .andExpect(status().isBadRequest());
    }

    private List<FavoriteExamDto> getFavoriteExamDtos() {
        List<FavoriteExamDto> favoriteExamDtos = new ArrayList<>();
        FavoriteExamDto favoriteExamDto1 = new FavoriteExamDto(1L, "title1", LocalDateTime.now(), "writer1");
        FavoriteExamDto favoriteExamDto2 = new FavoriteExamDto(2L, "title2", LocalDateTime.now(), "writer2");
        favoriteExamDtos.add(favoriteExamDto1);
        favoriteExamDtos.add(favoriteExamDto2);
        return favoriteExamDtos;
    }

    private OneExamDto getOneExamDto() {
        OneExamDto build = OneExamDto.builder()
                .title("title")
                .nickName("nickName")
                .num("num")
                .like(0)
                .build();
        return build;
    }

}