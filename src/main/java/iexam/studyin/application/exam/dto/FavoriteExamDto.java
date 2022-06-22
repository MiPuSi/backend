package iexam.studyin.application.exam.dto;

import iexam.studyin.application.exam.controller.ExamDto;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Data
public class FavoriteExamDto {
    private Long id;
    private String title;
    private LocalDateTime created;

    private String writer;

    public FavoriteExamDto(Long id, String title, LocalDateTime created, String writer) {
        this.id = id;
        this.title = title;
        this.created = created;
        this.writer = writer;
    }
}
