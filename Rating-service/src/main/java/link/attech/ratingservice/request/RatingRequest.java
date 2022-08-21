package link.attech.ratingservice.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RatingRequest {
    private String userId;
    private String movieId;
    private Integer rate;
}
