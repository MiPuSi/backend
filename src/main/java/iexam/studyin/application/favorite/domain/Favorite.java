package iexam.studyin.application.favorite.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@IdClass(Favorite.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "TB_Favorite")
public class Favorite implements Serializable {

    @Id
    @JoinColumn(name = "member_id")
    private Long member;

    @Id
    @JoinColumn(name = "exam_id")
    private Long exam;

    @Column(name = "favorite_create")
    private LocalDateTime creationDate;

    @Column(name = "favorite_modify")
    private LocalDateTime modifyDate;

    public Favorite(Long memberId, Long examId) {
        this.member = memberId;
        this.exam = examId;
        creationDate = LocalDateTime.now();
        modifyDate = LocalDateTime.now();
    }
}
