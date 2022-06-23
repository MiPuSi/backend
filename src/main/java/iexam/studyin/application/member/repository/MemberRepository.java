package iexam.studyin.application.member.repository;

import iexam.studyin.application.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);
    Optional<Member> findByNum(String num);

    @Query("select m from Member m " +
            "left join fetch m.exams e " +
            "where m.email = :email")
    Optional<Member> findAllExamsByEmail(@Param("email") String email);
}
