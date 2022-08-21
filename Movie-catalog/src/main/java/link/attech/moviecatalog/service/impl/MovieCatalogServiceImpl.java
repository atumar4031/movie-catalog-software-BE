package link.attech.moviecatalog.service.impl;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import link.attech.moviecatalog.entity.MovieCatalog;
import link.attech.moviecatalog.entity.Rating;
import link.attech.moviecatalog.response.BaseResponse;
import link.attech.moviecatalog.response.MovieInfo;
import link.attech.moviecatalog.response.UserRatings;
import link.attech.moviecatalog.service.MovieCatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class MovieCatalogServiceImpl implements MovieCatalogService {
    @Autowired
    private RestTemplate restTemplate;
    public final String MOVIE_INFO_SERVICE = "movieInfoService";

    @Override
    public BaseResponse getCatalog(String userId) {
        UserRatings ratings = getUserRatings(userId);

        assert ratings != null;

        List<MovieCatalog> collect = ratings.getRatings().stream().map(this::getMovieInfo).collect(Collectors.toList());
        return  new BaseResponse<>(HttpStatus.OK, "catalog received",collect);
    }

    @CircuitBreaker(name = MOVIE_INFO_SERVICE, fallbackMethod = "getUserRatingsService")
    private UserRatings getUserRatings(String userId) {
        UserRatings ratings = restTemplate.getForObject("http://RATING-DATA-SERVICE/api/v1/rating/" + userId, UserRatings.class);
        return ratings;
    }

    @CircuitBreaker(name = MOVIE_INFO_SERVICE, fallbackMethod = "getMovieInfoService")
    private MovieCatalog getMovieInfo(Rating rating) {
        MovieInfo movieInfo = restTemplate.getForObject("http://MOVIE-INFO-SERVICE/api/v1/movie/" + rating.getMovieId(), MovieInfo.class);

        return new MovieCatalog(rating.getUserId(), movieInfo.getMovieId(), movieInfo.getTitle(), movieInfo.getOverview(), rating.getRate());
    }

    public MovieCatalog getMovieInfoService(){
        return new MovieCatalog("101", "101", "pending movie", "pending movie description", 10);
    }

}
