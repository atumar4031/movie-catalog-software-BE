package link.attech.movieinfo.service.impl;

import link.attech.movieinfo.entity.Movie;
import link.attech.movieinfo.request.BaseResponse;
import link.attech.movieinfo.request.MovieResponse;
import link.attech.movieinfo.request.Query;
import link.attech.movieinfo.response.MovieDetails;
import link.attech.movieinfo.service.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${api.key}")
    private  String apiKey;

    @Override
    public Movie getMovieInfo(String movieId) {
        MovieDetails movieDetails = restTemplate.getForObject("https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" + apiKey, MovieDetails.class);
        assert movieDetails != null;
        return new Movie(movieId, movieDetails.getTitle(), movieDetails.getOverview());
    }

    @Override
    public BaseResponse<MovieResponse> findMovie(Query query) {
        MovieResponse movie = restTemplate.getForObject("https://api.themoviedb.org/3/search/movie?api_key=" + apiKey + "&query=" + query.getQuery(), MovieResponse.class);
        assert movie != null;
        return new BaseResponse<>(HttpStatus.OK, "Movies Result", movie);
    }
}
