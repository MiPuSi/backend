package iexam.studyin.application.exam.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "TB_Question")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qst_id")
    private Long id;

    @Column(name = "qst_text")
    private String questionText;

    @Column(name = "qst_image")
    private String questionImage;

    @Column(name = "ans_text")
    private String answerText;

    @Column(name = "ans_image")
    private String answerImage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exam_id")
    private Exam exam;

    @Builder
    public Question(String questionText, String questionImage, String answerText, String answerImage) {
        this.questionText = questionText;
        this.questionImage = questionImage;
        this.answerText = answerText;
        this.answerImage = answerImage;
    }

    public void addExam(Exam exam){
        exam.getQuestions().add(this);
        this.exam = exam;
    }
}
