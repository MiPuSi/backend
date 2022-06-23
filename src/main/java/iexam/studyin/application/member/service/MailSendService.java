package iexam.studyin.application.member.service;

import iexam.studyin.application.member.controller.dto.MailUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class MailSendService {

    private final JavaMailSender mailSender;

    public String sendAuthMail(String email) throws MessagingException {

        //6자리 난수 인증번호 생성
        String authKey = MailUtils.getKey(6);

        //인증메일 보내기
        MailUtils sendMail = new MailUtils(mailSender);
        sendMail.setSubject("회원가입 이메일 인증");
        sendMail.setText(new StringBuffer().append("<h1>[이메일 인증]</h1>")
                .append("<p>아래 링크를 클릭하시면 이메일 인증이 완료됩니다.</p>")
                .append("<a href='http://49.50.162.19:8000/api/member/signUpConfirm?email=")
                .append(email)
                .append("&authKey=")
                .append(authKey)
                .append("' target='_blenk'>이메일 인증 확인</a>")
                .toString());
//            sendMail.setFrom("이메일 주소", "관리자");
        sendMail.setTo(email);
        sendMail.send();

        return authKey;
    }
}