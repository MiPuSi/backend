package iexam.studyin;

import iexam.studyin.application.advice.domain.Advice;
import iexam.studyin.application.advice.repository.AdviceRepository;
import iexam.studyin.application.exam.domain.Exam;
import iexam.studyin.application.exam.domain.Question;
import iexam.studyin.application.exam.repository.ExamRepository;
import iexam.studyin.application.exam.service.ExamService;
import iexam.studyin.application.favorite.domain.Favorite;
import iexam.studyin.application.favorite.service.FavoriteService;
import iexam.studyin.application.member.domain.Member;
import iexam.studyin.application.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.*;

@Component
@RequiredArgsConstructor
public class TestData {

    private final MemberRepository memberRepository;
    private final ExamRepository examRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AdviceRepository adviceRepository;
    private final FavoriteService favoriteService;
    @PostConstruct
    @Transactional
    public void dummies(){
        //TODO 시험지 작성 유저 4
        String[] nicknames = {"호돌이","깜돌이","까불이","소중대"};
        String[] colleges = {"한국대학교","재능대학교","최고대학교","왕국대학교","열공대학교"};
        String[] exs = {"네트워크 개론","데이터 통신","알고리즘","운영체제","데이터베이스","리눅스 시스템","자바 프로그래밍","이산수학","선형대수","확률과 통계","객체지향 프래그래밍","회계원리","법학개론","영양학개론","영문학개론"};

        for(int i=0;i<20;i++){
            Member writer = Member.builder()
                    .creationDate(LocalDateTime.now())
                    .nickName(nicknames[i%4])
                    .email("abc2"+i+"@abc.com")
                    .num("2022060"+i)
                    .organization(colleges[i%5])
                    .password(passwordEncoder.encode("1234"))
                    .modifyDate(LocalDateTime.now())
                    .build();
            Member save = memberRepository.save(writer);
            for(int j=0;j<4;j++){
                int idx = (int) ((Math.random() * 10000) % 15);
                Exam build = Exam.builder()
                        .create(LocalDateTime.now())
                        .modify(LocalDateTime.now())
                        .member(save)
                        .title(colleges[j]+"_"+exs[idx])
                        .build();
                examRepository.save(build);
            }
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
        createUserFavorites();
        createActiveUser();
    }

    public void createActiveUser() {
        Member activeUser = Member.builder()
                .num("220624118")
                .email("happy@happy.com")
                .creationDate(LocalDateTime.now())
                .password(passwordEncoder.encode("1234"))
                .nickName("스펀지밥")
                .modifyDate(LocalDateTime.now())
                .organization("익잼대학교")
                .build();
        Member active = memberRepository.save(activeUser);

        List<Exam> all = examRepository.findAll();
        for(int i=0;i<5;i++){
            Exam exam = all.get(i);
            favoriteService.favoriteExam(active.getEmail(), exam.getId());
        }

        //TODO 질문지 - 데이터베이스
        Question question = new Question("트랜잭션, 동시성제어, 2단계 로킹에 대해 설명하시오", null, null, null);
        Question question2 = new Question("직렬 가능 스케줄은 무엇인가?", null, null, null);
        Question question3 = new Question("두 트랜잭션이 동시에 수핼될 때 언제 충돌하는가?", null, null, null);
        Question question4 = new Question("체크포인트의 목적은 무엇인가?", null, null, null);
        Question question5 = new Question("본인이 소속된 학교에 관한 ER-D를 작성하시오", null, null, null);
        Question question6 = new Question("인덱스 결정에 도움이 되는 지침을 설명하시오", null, null, null);
        Question question7 = new Question("정규화란?", null, null, null);
        Question question8 = new Question("관계대수 실렉트 연산자와 SQL의 Select의 차이를 설명하시오", null, null, null);
        Question question9 = new Question("데이터베이스는 왜 필요한가?", null, null, null);
        Question question10 = new Question("DDL, DML, DCL에 대해서 설명하시오", null, null, null);
        Exam build = Exam.builder()
                .create(LocalDateTime.now())
                .modify(LocalDateTime.now())
                .member(active)
                .title("익잼대학교_데이터베이스")
                .build();
        question.addExam(build);
        question2.addExam(build);
        question3.addExam(build);
        question4.addExam(build);
        question5.addExam(build);
        question6.addExam(build);
        question7.addExam(build);
        question8.addExam(build);
        question9.addExam(build);
        question10.addExam(build);
        examRepository.save(build);
    }

    public void createUserFavorites() {
        List<Member> members = memberRepository.findAll();
        List<Exam> exams = examRepository.findAll();
        ArrayList<Integer> al = new ArrayList<>(Arrays.asList(0,1,2,3,4,5,6,7));

        for(int i=0;i<32;i++) {
            Exam exam = exams.get(i);
            //member 8;
            //exam < 32
            Collections.shuffle(al);
            for(int j=0;j<20;j++){
                Member member = members.get(j);
                int idx = (int) ((Math.random() * 10000) % 3);
                if(idx==2)
                    favoriteService.favoriteExam(member.getEmail(), exam.getId());
            }
        }
    }
}
