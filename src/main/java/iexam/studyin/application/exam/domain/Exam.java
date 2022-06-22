package iexam.studyin.application.exam.domain;

import iexam.studyin.application.member.domain.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Entity
@Getter
@Table(name = "TB_Exam")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Exam {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exam_id")
    private Long id;

    @Column(name = "exam_title")
    private String title;

    @Column(name = "exam_create")
    private LocalDateTime create;

    @Column(name = "exam_modify")
    private LocalDateTime modify;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL)
    private List<Question> questions = new ArrayList<>();

    @OneToMany(mappedBy = "exam")
    private List<Favorite> favoriteList = new LinkedList<>();

    @Builder
    public Exam(String title, LocalDateTime create, LocalDateTime modify,Member member) {
        this.title = title;
        this.create = create;
        this.modify = modify;
        this.member = member;
        member.getExams().add(this);
    }

    public void addQuestion(Question question) {
        this.questions.add(question);
    }
}
