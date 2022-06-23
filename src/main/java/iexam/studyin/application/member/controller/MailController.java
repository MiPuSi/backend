package iexam.studyin.application.member.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import iexam.studyin.application.member.controller.dto.AuthEmailDto;
import iexam.studyin.application.member.controller.dto.MemberDto;
import iexam.studyin.application.member.service.AuthKeyService;
import iexam.studyin.application.member.service.MailSendService;
import iexam.studyin.core.validation.AddMemberValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MailController {

    private final AuthKeyService authKeyRepository;
    private final MailSendService mss;
    private final AddMemberValidation validation;

    @PostMapping("/api/member/email")
    @ResponseBody
    public ResponseEntity signUp(@Valid MemberDto memberDto, BindingResult bindingResult) throws JsonProcessingException, MessagingException {

        validation.validate(memberDto, bindingResult);

        if (bindingResult.hasErrors()) {
            log.error("errors={}", bindingResult);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        authKeyRepository.deleteAuth(memberDto.getEmail());

        String authKey = mss.sendAuthMail(memberDto.getEmail());
        memberDto.setAuthKey(authKey);

        authKeyRepository.saveAuth(memberDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/api/member/signUpConfirm")
    public void authEmail(@ModelAttribute AuthEmailDto authEmailDto) throws JsonProcessingException {
        authKeyRepository.checkAuth(authEmailDto.getEmail(), authEmailDto.getAuthKey());
    }

    @GetMapping("/test/test")
    public void redirectTest(HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.sendRedirect("http://localhost:8080/StudyIn/main.jsp?success=1");
    }
}
