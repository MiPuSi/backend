package iexam.studyin.application.exam.domain;

import iexam.studyin.application.member.domain.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "TB_Exam")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Exam {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exam_id")
    private Long id;

    @Column(name = "exam_create")
    private LocalDateTime create;

    @Column(name = "exam_modify")
    private LocalDateTime modify;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
}
