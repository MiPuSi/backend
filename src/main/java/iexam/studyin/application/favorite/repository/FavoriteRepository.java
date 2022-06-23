package iexam.studyin.application.favorite.repository;

import iexam.studyin.application.favorite.domain.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<Favorite,Long> {
}
