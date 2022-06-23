package iexam.studyin.application.exam.service;

import iexam.studyin.application.exam.controller.dto.*;
import iexam.studyin.application.exam.domain.Exam;
import iexam.studyin.application.exam.domain.Question;
import iexam.studyin.application.exam.repository.ExamRepository;
import iexam.studyin.application.favorite.controller.dto.FavoriteExamDto;
import iexam.studyin.application.member.domain.Member;
import iexam.studyin.application.member.repository.MemberRepository;
import iexam.studyin.core.config.auth.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
            question.addExam(exam);
            //exam.addQuestion(question);
        }

        examRepository.save(exam);
    }

    private Member isMember(PrincipalDetails principal) {
        Member member = memberRepository.findByEmail(principal.getUsername())
                .orElseThrow(RuntimeException::new);
        return member;
    }

    @Transactional
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

    public List<FavoriteExamDto> findMyExams(String email) {
        Optional<Member> memberOpt = memberRepository.findAllExamsByEmail(email);
        if (memberOpt.isEmpty()) throw new BadCredentialsException("User Not Found");
        Member member = memberOpt.get();
        if (member.getExams() == null) return null;
        else {
            List<Exam> exams = member.getExams();
            List<FavoriteExamDto> examList = exams.stream()
                    .map(e -> new FavoriteExamDto(e.getId(), e.getTitle(), e.getCreate(), e.getMember().getNickName()))
                    .collect(Collectors.toList());
            return examList;
        }
    }


    public PageResponse findExamByKeyWord(String title, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Exam> examByTitle = examRepository.findExamByTitle(title, pageRequest);

        List<Exam> content = examByTitle.getContent();
        List<ExamResponse> examsContent = content.stream().map(c -> new ExamResponse(c.getMember().getNum(), c.getTitle(), c.getFavoriteList().size(),
                c.getMember().getNickName())).collect(Collectors.toList());


        PageResponse examSearchList = PageResponse.builder()
                .exams(examsContent)
                .currentPage(examByTitle.getPageable().getPageNumber() + 1)
                .totalPage(examByTitle.getTotalPages())
                .total(examByTitle.getNumberOfElements())
                .build();
        return examSearchList;
    }

    public PageResponse findExamsByLikes() {
        PageRequest pageRequest = PageRequest.of(0, 10);
        List<Exam> examsOrderByLike = examRepository.findExamsOrderByLike(pageRequest);
        List<ExamResponse> examResponses = examsOrderByLike.stream()
                .map(e -> new ExamResponse(e.getMember().getNum(),
                        e.getTitle(), e.getFavoriteList().size(), e.getMember().getNickName()))
                .collect(Collectors.toList());

        PageResponse pageResponse = PageResponse.builder()
                .total(examsOrderByLike.size())
                .totalPage(1)
                .currentPage(1)
                .exams(examResponses)
                .build();
        return pageResponse;
    }
}
