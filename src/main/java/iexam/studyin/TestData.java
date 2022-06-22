package iexam.studyin;

import iexam.studyin.application.exam.domain.Exam;
import iexam.studyin.application.exam.repository.ExamRepository;
import iexam.studyin.application.member.domain.Member;
import iexam.studyin.application.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class TestData {

    private final MemberRepository memberRepository;
    private final ExamRepository examRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @PostConstruct
    @Transactional
    public void dummies(){

        Member writer = Member.builder()
                .creationDate(LocalDateTime.now())
                .nickName("시험지작성한유저")
                .email("abc22@abc.com")
                .num("1234123")
                .organization("한국대학교")
                .password(passwordEncoder.encode("1234"))
                .modifyDate(LocalDateTime.now())
                .build();
        Member save = memberRepository.save(writer);

        Member tempMember = Member.builder()
                .creationDate(LocalDateTime.now())
                .email("abc@abc.com")
                .nickName("시험지좋아하는유저")
                .num("1234")
                .organization("한국대학교")
                .password(passwordEncoder.encode("1234"))
                .modifyDate(LocalDateTime.now())
                .build();
        memberRepository.save(tempMember);


        for(int i=0;i<12;i++){
            Exam build = Exam.builder()
                    .create(LocalDateTime.now())
                    .modify(LocalDateTime.now())
                    .member(save)
                    .title("문제" + i)
                    .build();
            examRepository.save(build);

        }
    }
}
