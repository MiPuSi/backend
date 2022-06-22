package iexam.studyin.application.favorite.controller.dto;

import lombok.Data;

import java.time.LocalDateTime;

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
