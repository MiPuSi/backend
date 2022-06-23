package iexam.studyin.application.exam.repository;

import iexam.studyin.application.exam.domain.Exam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {
    @Query("select e from Exam e " +
            "left join e.favoriteList f " +
            "join fetch e.member m " +
            "where f.member = :mid and " +
            "f.exam = e.id")
    List<Exam> findFavoriteExam(@Param("mid") Long id);

    @Query(value = "select e from Exam e " +
            "left join fetch e.member m " +
            "left join fetch e.favoriteList fl " +
            "where e.title like %:t%",
    countQuery = "select count(e) from Exam e " +
            "where e.title like %:t%")
    Page<Exam> findExamByTitle(@Param("t") String title, Pageable pageable);

    @Query("select e from Exam e " +
            "left join fetch e.member m " +
            "left join fetch e.favoriteList fl " +
            "order by size(e.favoriteList) desc ")
    List<Exam> findExamsOrderByLike(Pageable pageable);
}
