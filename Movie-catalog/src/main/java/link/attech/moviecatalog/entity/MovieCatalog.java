package link.attech.moviecatalog.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MovieCatalog {
    private String userId;
    private String movieId;
    private String title;
    private String overview;
    private Integer rating;
}
