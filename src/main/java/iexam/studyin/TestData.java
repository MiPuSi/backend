package iexam.studyin;

import iexam.studyin.application.advice.domain.Advice;
import iexam.studyin.application.advice.repository.AdviceRepository;
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
    private final AdviceRepository adviceRepository;

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


        for(int i=0;i<5;i++){
            Exam build = Exam.builder()
                    .create(LocalDateTime.now())
                    .modify(LocalDateTime.now())
                    .member(save)
                    .title("문제" + i)
                    .build();
            examRepository.save(build);
        }

        Advice advice = Advice.builder()
                .content("글을 읽어도 성현을 보지 못한다면 지필(紙筆)의 종일 뿐이고 벼슬자리에 있어도 백성을 사랑하지 않는다면 관복 입은 도둑에 지나지 않는다. 학문을 하면서도 몸소 실천함을 숭상하지 않는다면 입으로만 참선하는 사람일 뿐이요, 큰일을 일으키고도 은덕을 심지 않는다면 눈앞에서 잠시 피다가 지는 꽃일 뿐이다.")
                .adviser("-채근담")
                .build();

        Advice advice1 = Advice.builder()
                .content("가장 유능한 사람은 가장 배우기 힘쓰는 사람이다.")
                .adviser("-괴테")
                .build();

        adviceRepository.save(advice);
        adviceRepository.save(advice1);
    }
}
