package iexam.studyin.application.advice.controller;

import iexam.studyin.application.advice.domain.Advice;
import iexam.studyin.application.advice.service.AdviceService;
import iexam.studyin.application.member.domain.Member;
import iexam.studyin.application.member.service.MemberService;
import iexam.studyin.core.config.auth.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class HomeController {

    private final AdviceService adviceService;
    private final MemberService memberService;

    @GetMapping("/api/home")
    public ResponseEntity<HomeDto> homeDto(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        HomeDto homeDto = new HomeDto();
        if (principalDetails != null) {
            Member member = memberService.findMemberIdByEmail(principalDetails.getUsername());
            homeDto.setNickName(member.getNickName());
            homeDto.setNum(member.getNum());
            homeDto.setOrganization(member.getOrganization());
        }
        Advice advice = adviceService.randOne();
        homeDto.setAdvice(advice.getContent());
        homeDto.setAdviser(advice.getAdviser());
        return new ResponseEntity<>(homeDto, HttpStatus.OK);
    }
}
