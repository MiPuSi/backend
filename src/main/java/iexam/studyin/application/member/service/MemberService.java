package iexam.studyin.application.member.service;

import iexam.studyin.application.member.controller.dto.MailUtils;
import iexam.studyin.application.member.controller.dto.MemberDto;
import iexam.studyin.application.member.domain.Member;
import iexam.studyin.application.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;

    @Transactional
    public void signUp(MemberDto memberDto) {
        LocalDateTime now = LocalDateTime.now();
        String num = now.format(DateTimeFormatter.ofPattern("yyyyMMdd")).substring(2);
        while (true) {
            String rand = MailUtils.getKey(3);
            if (memberRepository.findByNum(num + rand).isEmpty()) {
                num += rand;
                break;
            }
        }

        //암호화
        String encode = passwordEncoder.encode(memberDto.getPassword());
        Member member = Member.builder()
                .email(memberDto.getEmail())
                .password(encode)
                .creationDate(now)
                .modifyDate(now)
                .num(num)
                .build();

        memberRepository.save(member);
    }

    public long findMemberIdByEmail(String email) {
        Member byEmail = memberRepository.findByEmail(email)
                .orElseThrow(RuntimeException::new);

        return byEmail.getId();
    }

    public boolean isAlreadyMember(String email) {
        Optional<Member> bySocial = memberRepository.findByEmail(email);
        if (bySocial.isEmpty()) return false;
        return true;
    }
}
