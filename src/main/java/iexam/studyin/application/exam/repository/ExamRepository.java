package iexam.studyin.application.exam.repository;

import iexam.studyin.application.exam.domain.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {
    @Query("select e from Exam e " +
            "left join e.favoriteList f " +
            "join fetch e.member m " +
            "where f.member = :mid and " +
            "f.exam = e.id")
    List<Exam> findFavoriteExam(@Param("mid") Long id);
}
