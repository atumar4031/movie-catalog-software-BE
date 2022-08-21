package link.attech.movieinfo.controller;


import link.attech.movieinfo.entity.Movie;
import link.attech.movieinfo.request.BaseResponse;
import link.attech.movieinfo.request.MovieResponse;
import link.attech.movieinfo.request.Query;
import link.attech.movieinfo.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/movie")
public class movieInfoController {

    //TODO: get user id from login context

    @Autowired
    private MovieService movieService;

    @GetMapping("/{movieId}")
    public Movie getMovieInfo(@PathVariable String movieId){
        return  movieService.getMovieInfo(movieId);
    }

    @PostMapping
    public BaseResponse<MovieResponse> findMovie(@RequestBody Query query){
        return movieService.findMovie(query);
    }
}
