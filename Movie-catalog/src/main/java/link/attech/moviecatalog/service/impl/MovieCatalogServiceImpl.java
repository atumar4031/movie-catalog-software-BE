package link.attech.moviecatalog.service.impl;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import link.attech.moviecatalog.entity.MovieCatalog;
import link.attech.moviecatalog.entity.Rating;
import link.attech.moviecatalog.entity.Role;
import link.attech.moviecatalog.entity.User;
import link.attech.moviecatalog.repository.RoleRepository;
import link.attech.moviecatalog.repository.UserRepository;
import link.attech.moviecatalog.request.LoginRequest;
import link.attech.moviecatalog.request.UserRequest;
import link.attech.moviecatalog.request.UserToRole;
import link.attech.moviecatalog.response.*;
import link.attech.moviecatalog.service.MovieCatalogService;
import link.attech.moviecatalog.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Slf4j
@Service
public class MovieCatalogServiceImpl implements MovieCatalogService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    public final String MOVIE_INFO_SERVICE = "movieInfoService";


    @Override
    public BaseResponse getCatalog() {
        // TODO: Get user from security context
        User user = retrieveUserDetails().getUser();
        UserRatings ratings = getUserRatings(user.getUserId().toString());

        assert ratings != null;

        List<MovieCatalog> collect = ratings.getRatings().stream().map(this::getMovieInfo).collect(Collectors.toList());
        return  new BaseResponse<>(HttpStatus.OK, "catalog received",collect);
    }

    // Helper Methods
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

    private SecurityContextUser retrieveUserDetails() {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new SecurityContextUser(user);
    }
}
