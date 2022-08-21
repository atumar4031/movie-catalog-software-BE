package link.attech.movieinfo.service;

import link.attech.movieinfo.entity.Movie;
import link.attech.movieinfo.request.BaseResponse;
import link.attech.movieinfo.request.MovieResponse;
import link.attech.movieinfo.request.Query;

public interface MovieService {
    Movie getMovieInfo(String movieId);
    BaseResponse<MovieResponse> findMovie(Query query);
}
