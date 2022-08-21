package link.attech.ratingservice.response;

import link.attech.ratingservice.entity.Rating;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserRatings {
    private List<Rating> ratings;
}
