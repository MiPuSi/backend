package iexam.studyin.application.member.domain;

import iexam.studyin.application.exam.domain.Exam;
import iexam.studyin.application.favorite.domain.Favorite;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @Column(name = "member_nick_name")
    private String nickName;

    @Column(name = "member_organization")
    private String organization;

    @Column(name = "member_create")
    private LocalDateTime creationDate;

    @Column(name = "member_modify")
    private LocalDateTime modifyDate;

    @OneToMany(mappedBy = "member")
    private List<Exam> exams = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Favorite> favorites = new ArrayList<>();

    @Builder
    public Member(String organization, String email, String password, String num, LocalDateTime creationDate, LocalDateTime modifyDate, String nickName) {
        this.email = email;
        this.password = password;
        this.num = num;
        this.creationDate = creationDate;
        this.modifyDate = modifyDate;
        this.nickName = nickName;
        this.organization = organization;
    }

    public void addFavorite(Favorite favorite) {
        this.favorites.add(favorite);
    }
}
