package iexam.studyin.application.exam.controller.dto;

import lombok.Data;

@Data
public class ExamResponse {
    private String num; //학번
    private String title; //시험지 이름
    private int like; //해당 시험지 좋아요
    private String nickName; //작성자 닉네임

    public ExamResponse(String num, String title, int like, String nickName) {
        this.num = num;
        this.title = title;
        this.like = like;
        this.nickName = nickName;
    }
}
