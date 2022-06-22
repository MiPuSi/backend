package iexam.studyin;

import iexam.studyin.application.member.domain.Member;
import iexam.studyin.application.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class TestData {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @PostConstruct
    @Transactional
    public void dummies(){
        Member tempMember = Member.builder()
                .creationDate(LocalDateTime.now())
                .email("abc@abc.com")
                .num("1234")
                .password(passwordEncoder.encode("1234"))
                .modifyDate(LocalDateTime.now())
                .build();
        memberRepository.save(tempMember);
    }
}
