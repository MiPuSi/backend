package iexam.studyin.application.member.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import iexam.studyin.application.member.controller.dto.AuthEmailDto;
import iexam.studyin.application.member.controller.dto.MemberDto;
import iexam.studyin.application.member.service.AuthKeyService;
import iexam.studyin.application.member.service.MailSendService;
import iexam.studyin.core.validation.AddMemberValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MailController {

    private final AuthKeyService authKeyRepository;
    private final MailSendService mss;
    private final AddMemberValidation validation;

    @PostMapping("/member/signUp/email")
    public void signUp(@Valid MemberDto memberDto, BindingResult bindingResult) throws JsonProcessingException, MessagingException {

        validation.validate(memberDto, bindingResult);

        if (bindingResult.hasErrors()) {
            log.error("errors={}", bindingResult);
            return ;
        }

        // 원래 로직 - 멤버 DB로 미리 회원을 저장
        authKeyRepository.deleteAuth(memberDto.getEmail());

        //임의의 authKey 생성 & 이메일 발송
        String authKey = mss.sendAuthMail(memberDto.getEmail());
        memberDto.setAuthKey(authKey);

        authKeyRepository.saveAuth(memberDto);
    }

    @GetMapping("member/signUpConfirm")
    public void authEmail(@ModelAttribute AuthEmailDto authEmailDto) throws JsonProcessingException {
        authKeyRepository.checkAuth(authEmailDto.getEmail(), authEmailDto.getAuthKey());
    }
}
