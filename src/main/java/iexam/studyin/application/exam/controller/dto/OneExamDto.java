package iexam.studyin.application.exam.controller.dto;

import iexam.studyin.application.exam.domain.Question;
import lombok.*;

import java.util.List;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OneExamDto {

    private String num;
    private String nickName;
    private String title;
    private int like;
    private List<QOrA> questions;

    @Builder
    public OneExamDto(String num, String title, int like, List<QOrA> questions, String nickName) {
        this.num = num;
        this.title = title;
        this.like = like;
        this.questions = questions;
        this.nickName = nickName;
    }
}
