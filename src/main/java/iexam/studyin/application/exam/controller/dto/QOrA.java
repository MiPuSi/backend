package iexam.studyin.application.exam.controller.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QOrA {

    private String QOrA;
    private String QOrAImage;

    @Builder
    public QOrA(String QOrA, String QOrAImage) {
        this.QOrA = QOrA;
        this.QOrAImage = QOrAImage;
    }
}
