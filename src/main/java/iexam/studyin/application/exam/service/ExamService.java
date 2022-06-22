package iexam.studyin.application.exam.service;

import iexam.studyin.application.exam.controller.dto.ExamDto;
import iexam.studyin.application.exam.controller.dto.OneExamDto;
import iexam.studyin.application.exam.controller.dto.QOrA;
import iexam.studyin.application.exam.controller.dto.QuestionDto;
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
import java.util.ArrayList;
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

        List<QuestionDto> questionDtos = examDto.getQuestionDtos();

        for (QuestionDto questionDto : questionDtos) {
            String questionImage = imageStore.storeFile(questionDto.getQuestionImage());
            String answerImage = imageStore.storeFile(questionDto.getAnswerImage());
            Question question = Question.builder()
                    .questionText(questionDto.getQuestion())
                    .questionImage(questionImage)
                    .answerText(questionDto.getAnswer())
                    .answerImage(answerImage)
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

    public OneExamDto findByExamId(Long examId) {
        Exam exam = examRepository.findById(examId)
                .orElseThrow(RuntimeException::new);
        List<QOrA> qOrAS = new ArrayList<>();
        for (Question question : exam.getQuestions()) {
            QOrA q = QOrA.builder()
                    .QOrA(question.getQuestionText())
                    .QOrAImage(question.getQuestionImage())
                    .build();
            qOrAS.add(q);
            QOrA a = QOrA.builder()
                    .QOrA(question.getAnswerText())
                    .QOrAImage(question.getAnswerImage())
                    .build();
            qOrAS.add(a);
        }

        OneExamDto oneExamDto = OneExamDto.builder()
                .questions(qOrAS)
                .like(exam.getFavoriteList().size())
                .num(exam.getMember().getNum())
                .nickName(exam.getMember().getNickName())
                .title(exam.getTitle())
                .build();

        return oneExamDto;
    }
}
