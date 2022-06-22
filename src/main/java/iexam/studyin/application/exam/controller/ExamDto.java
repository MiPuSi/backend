package iexam.studyin.application.exam.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.Multipart;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class ExamDto {
    private List<String> question = new ArrayList<>();
    private List<MultipartFile> questionImage = new ArrayList<>();
    private List<String> answer = new ArrayList<>();
    private List<MultipartFile> answerImage = new ArrayList<>();
    private String num;
    private String schoolName;
    private String lessonName;
    private String freeName;
}
