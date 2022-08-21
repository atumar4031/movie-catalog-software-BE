package link.attech.movieinfo.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import link.attech.movieinfo.response.MovieDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MovieResponse {
    @JsonProperty("total_pages")
    private Integer totalPage;
    @JsonProperty("total_results")
    private Integer totalResult;
    private List<MovieDetails> results = new ArrayList<>();

}
