package link.attech.ratingservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "rating")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rateId;
    private String userId;
    private String movieId;
    private Integer rate;

    public Rating(String userId, String movieId, Integer rate) {
        this.userId = userId;
        this.movieId = movieId;
        this.rate = rate;
    }
}
