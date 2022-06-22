package iexam.studyin.application.exam.controller;

import iexam.studyin.application.exam.controller.dto.ExamDto;
import iexam.studyin.application.exam.controller.dto.OneExamDto;
import iexam.studyin.application.exam.controller.dto.QuestionDto;
import iexam.studyin.application.exam.service.ExamService;
import iexam.studyin.application.favorite.controller.dto.FavoriteExamDto;
import iexam.studyin.application.favorite.controller.dto.MyFavoriteExamResponse;
import iexam.studyin.core.config.auth.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UploadExamController {

    private final ExamService uploadExamService;

    @PostMapping("/api/exam/upload")
    public ResponseEntity uploadExam(ExamDto examDto,
                                     @AuthenticationPrincipal PrincipalDetails principal) throws IOException {
        List<QuestionDto> questionDtos = examDto.getQuestionDtos();
        for (QuestionDto questionDto : questionDtos) {
            System.out.println("questionDto.getQuestion() = " + questionDto.getQuestion());
        }
        uploadExamService.upload(examDto, principal);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/api/exam/{id}")
    public OneExamDto lookUpOne(@PathVariable Long examId) {
        OneExamDto oneExamDto = uploadExamService.findByExamId(examId);
        return oneExamDto;
    }

    //Response & DTO 재탕
    //여기선 Favorite이 아닌 내가 작성한 시험지입니다.
    @GetMapping("/api/myExam")
    public ResponseEntity<MyFavoriteExamResponse> myExamsAll(@AuthenticationPrincipal PrincipalDetails user){
        List<FavoriteExamDto> myExams = uploadExamService.findMyExams(user.getUsername());
        MyFavoriteExamResponse allExams = MyFavoriteExamResponse.builder()
                .exams(myExams)
                .count(myExams.size())
                .httpStatus(HttpStatus.OK)
                .message("마이 시험지")
                .build();

        return new ResponseEntity<>(allExams,allExams.getHttpStatus());
    }
}
