package iexam.studyin.application.member.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
@Table(name = "TB_Member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "member_email")
    private String email;

    @Column(name = "member_password")
    private String password;

    @Column(name = "member_num")
    private String num;

    @Column(name = "member_create")
    private LocalDateTime creationDate;

    @Column(name = "member_modify")
    private LocalDateTime modifyDate;

    @Builder
    public Member(String email, String password, String num, LocalDateTime creationDate, LocalDateTime modifyDate) {
        this.email = email;
        this.password = password;
        this.num = num;
        this.creationDate = creationDate;
        this.modifyDate = modifyDate;
    }
}
