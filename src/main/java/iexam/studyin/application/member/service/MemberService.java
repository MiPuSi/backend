package iexam.studyin.application.member.service;

import iexam.studyin.application.member.controller.dto.MemberDto;
import iexam.studyin.application.member.domain.Member;
import iexam.studyin.application.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public void signUp(MemberDto memberDto) {
        Member member = Member.builder()
                .email(memberDto.getEmail())
                .password(memberDto.getPassword())
                .creationDate(LocalDateTime.now())
                .modifyDate(LocalDateTime.now())
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
