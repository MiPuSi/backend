package iexam.studyin.application.favorite.controller;

import iexam.studyin.application.exam.domain.Exam;
import iexam.studyin.application.favorite.controller.dto.FavoriteExamDto;
import iexam.studyin.application.favorite.controller.dto.MyFavoriteExamResponse;
import iexam.studyin.application.favorite.service.FavoriteService;
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

    @GetMapping("/myExam/favorite")
    public ResponseEntity<MyFavoriteExamResponse> myFavoriteExam(@AuthenticationPrincipal PrincipalDetails user){
        List<Exam> favoriteExam = favoriteService.findFavoriteExam(user.getUsername());
        List<FavoriteExamDto> favoriteExamDtos
                = favoriteExam
                .stream()
                .map(f -> new FavoriteExamDto(f.getId(),f.getTitle(),f.getCreate(),f.getMember().getNickName()))
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
