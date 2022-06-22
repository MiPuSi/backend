package iexam.studyin.application.exam.controller;

import iexam.studyin.application.exam.service.ExamService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UploadExamController {

    private final ExamService uploadExamService;

    @PostMapping("/exam/upload")
    public ResponseEntity uploadExam(ExamDto examDto) {
        return new ResponseEntity(HttpStatus.OK);
    }
}
