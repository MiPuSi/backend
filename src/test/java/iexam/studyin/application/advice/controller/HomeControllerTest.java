package iexam.studyin.application.advice.controller;

import iexam.studyin.application.advice.domain.Advice;
import iexam.studyin.application.advice.service.AdviceService;
import iexam.studyin.application.member.domain.Member;
import iexam.studyin.application.member.service.MemberService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
public class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MemberService memberService;

    @MockBean
    private AdviceService adviceService;

    @Test
    public void abc() throws Exception {
        Member member = Member.builder()
                .num("num1")
                .nickName("nickName1")
                .organization("organization1")
                .build();
        doReturn(member).when(memberService).findMemberIdByEmail(any());
        Advice advice = Advice.builder()
                .content("content1")
                .adviser("adviser1")
                .build();
        doReturn(advice).when(adviceService).randOne();

        this.mockMvc.perform(get("/api/home"))
                .andExpect(status().isOk());
    }
}