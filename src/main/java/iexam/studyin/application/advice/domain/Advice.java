package iexam.studyin.application.advice.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "TB_Advice")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Advice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "advice_id")
    private Long id;

    @Column(name = "advice_content")
    private String content;

    @Column(name = "advice_adviser")
    private String adviser;

    @Builder
    public Advice(String content, String adviser) {
        this.content = content;
        this.adviser = adviser;
    }
}
