package iexam.studyin.application.member.repository;

import iexam.studyin.application.member.controller.dto.MemberDto;
import iexam.studyin.application.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);
    Optional<Member> findByNum(String num);
}
