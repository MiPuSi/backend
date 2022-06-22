package iexam.studyin.application.exam.controller;

import iexam.studyin.application.exam.domain.Exam;
import iexam.studyin.application.exam.dto.FavoriteExamDto;
import iexam.studyin.application.exam.dto.response.MyFavoriteExamResponse;
import iexam.studyin.application.exam.service.FavoriteService;
import iexam.studyin.core.config.auth.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class FavoriteController {

    private final FavoriteService favoriteService;

    @PostMapping("/exam/{id}/favorite")
    public ResponseEntity<String> likeExam(@PathVariable("id") Long id,
                                           @AuthenticationPrincipal PrincipalDetails user){
        favoriteService.favoriteExam(user.getUsername(),id);

        return ResponseEntity.ok("Ok");
    }

    //TODO EXAM 관련 주석처리 되어있습니다.
    @GetMapping("/myExam/favorite")
    public ResponseEntity<MyFavoriteExamResponse> myFavoriteExam(@AuthenticationPrincipal PrincipalDetails user){
        List<Exam> favoriteExam = favoriteService.findFavoriteExam(user.getUsername());
        List<FavoriteExamDto> favoriteExamDtos
                = favoriteExam
                .stream()
                .map(f -> new FavoriteExamDto(f.getId(),f.getTitle(),f.getCreate(),f.getMember().getEmail()))
                .collect(Collectors.toList());

        MyFavoriteExamResponse response = MyFavoriteExamResponse.builder()
                .exams(favoriteExamDtos)
                .count(favoriteExamDtos.size())
                .httpStatus(HttpStatus.OK)
                .message("내가 좋아요한 시험지")
                .build();

        return new ResponseEntity<>(response,response.getHttpStatus());
    }

}
