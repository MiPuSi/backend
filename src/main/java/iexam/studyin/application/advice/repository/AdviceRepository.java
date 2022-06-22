package iexam.studyin.application.advice.repository;

import iexam.studyin.application.advice.domain.Advice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AdviceRepository extends JpaRepository<Advice, Long> {
}
