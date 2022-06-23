package iexam.studyin.application.exam.controller.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class ExamDto {
    private List<QuestionDto> questionDtos = new ArrayList<>();
    private String num;
    private String schoolName;
    private String lessonName;
    private String freeName;
}
