package iexam.studyin.application.exam.dto.response;

import iexam.studyin.application.exam.dto.FavoriteExamDto;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.LinkedList;
import java.util.List;

@Data
@Builder
public class MyFavoriteExamResponse {
    private int count;
    private HttpStatus httpStatus;
    private String message;

    @Builder.Default
    private List<FavoriteExamDto> exams = new LinkedList<>();




}
