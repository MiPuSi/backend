package iexam.studyin.application.exam.repository;

import iexam.studyin.application.exam.domain.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<Favorite,Long> {
}
