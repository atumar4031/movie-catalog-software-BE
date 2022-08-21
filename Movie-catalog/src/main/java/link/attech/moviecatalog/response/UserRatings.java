package link.attech.moviecatalog.response;

import link.attech.moviecatalog.entity.Rating;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserRatings {
    private List<Rating> ratings = new ArrayList<>();
}
