package iexam.studyin.application.favorite.service;

import iexam.studyin.application.exam.domain.Exam;
import iexam.studyin.application.favorite.domain.Favorite;
import iexam.studyin.application.exam.repository.ExamRepository;
import iexam.studyin.application.member.domain.Member;
import iexam.studyin.application.favorite.repository.FavoriteRepository;
import iexam.studyin.application.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class FavoriteService {
    private final MemberRepository memberRepository;
    private final ExamRepository examRepository;
    private final FavoriteRepository favoriteRepository;

    @Transactional
    public void favoriteExam(String email, Long examId) {
        Optional<Member> byEmail = memberRepository.findByEmail(email);
        Optional<Exam> byExam = examRepository.findById(examId);
        if (byEmail.isEmpty() || byExam.isEmpty())
            throw new IllegalStateException("Add Favorite Failed");

        Member member = byEmail.get();
        Exam exam = byExam.get();

        Favorite favorite = new Favorite(member.getId(), exam.getId());
        member.addFavorite(favorite);
        exam.addFavorite(favorite);

        favoriteRepository.save(favorite);
    }


    public List<Exam> findFavoriteExam(String username) {
        Optional<Member> byEmail = memberRepository.findByEmail(username);
        if (byEmail.isEmpty()) throw new BadCredentialsException("not found user");
        Member member = byEmail.get();
        List<Exam> favoriteExam = examRepository.findFavoriteExam(member.getId());
        return favoriteExam;
    }
}
