package link.attech.ratingservice.repository;

import link.attech.ratingservice.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface RatingRepository extends JpaRepository<Rating, Long> {
    List<Rating> findAllByUserId(String userId);
}
