package iexam.studyin.application.exam.controller.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter @Setter
public class QuestionDto {
    private String question;
    private String questionImage;
    private String answer;
    private String answerImage;
}
