package iexam.studyin.application.exam.controller;

import iexam.studyin.application.exam.service.ExamService;
import iexam.studyin.core.config.auth.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UploadExamController {

    private final ExamService uploadExamService;

    @PostMapping("/api/exam/upload")
    public ResponseEntity uploadExam(ExamDto examDto,
                                     @AuthenticationPrincipal PrincipalDetails principal) throws IOException {
        uploadExamService.upload(examDto, principal);
        return new ResponseEntity(HttpStatus.OK);
    }
}
