package iexam.studyin.application.exam.controller.dto;

import lombok.Data;

@Data
public class ExamResponse {
    private String num; //학번
    private String title;
    private int like;
    private String nickName;

    public ExamResponse(String num, String title, int like, String nickName) {
        this.num = num;
        this.title = title;
        this.like = like;
        this.nickName = nickName;
    }
}
