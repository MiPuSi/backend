package iexam.studyin.application.exam.service;

import iexam.studyin.application.exam.controller.ExamDto;
import iexam.studyin.application.exam.domain.Exam;
import iexam.studyin.application.exam.domain.Question;
import iexam.studyin.application.exam.repository.ExamRepository;
import iexam.studyin.application.member.domain.Member;
import iexam.studyin.application.member.repository.MemberRepository;
import iexam.studyin.core.config.auth.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExamService {

    private final ExamRepository examRepository;
    private final MemberRepository memberRepository;
    private final ImageStore imageStore;


    @Transactional
    public void upload(ExamDto examDto, PrincipalDetails principal) throws IOException {
        Member member = isMember(principal);

        String schoolName = examDto.getSchoolName();
        String lessonName = examDto.getLessonName();
        String freeName = examDto.getFreeName() == null ? "" : "_" + examDto.getFreeName();

        Exam exam = Exam.builder()
                .title(schoolName + "_" + lessonName + freeName)
                .create(LocalDateTime.now())
                .modify(LocalDateTime.now())
                .member(member)
                .build();

        List<String> questionImage = imageStore.storeFiles(examDto.getQuestionImage());
        List<String> answerImage = imageStore.storeFiles(examDto.getAnswerImage());
        List<String> questionText = examDto.getQuestion();
        List<String> answerText = examDto.getAnswer();

        for (int i=0; i<examDto.getQuestion().size(); i++) {
            Question question = Question.builder()
                    .questionText(questionText.get(i))
                    .questionImage(questionImage.size() > i ? questionImage.get(i) : null)
                    .answerText(answerText.size() > i ? answerText.get(i) : null)
                    .answerImage(answerImage.size() > i ? answerImage.get(i) : null)
                    .build();

            exam.addQuestion(question);
        }

        examRepository.save(exam);
    }

    private Member isMember(PrincipalDetails principal) {
        Member member = memberRepository.findByEmail(principal.getUsername())
                .orElseThrow(RuntimeException::new);
        return member;
    }
}
