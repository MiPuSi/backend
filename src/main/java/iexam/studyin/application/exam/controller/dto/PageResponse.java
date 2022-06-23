package iexam.studyin.application.exam.controller.dto;

import lombok.Builder;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
@Builder
public class PageResponse {

    private int total;
    private int currentPage;
    private int totalPage;

    @Builder.Default
    private List<ExamResponse> exams = new LinkedList<>();

}
