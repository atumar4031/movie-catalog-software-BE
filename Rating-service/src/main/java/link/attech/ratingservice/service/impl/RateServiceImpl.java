package link.attech.ratingservice.service.impl;

import link.attech.ratingservice.entity.Rating;
import link.attech.ratingservice.repository.RatingRepository;
import link.attech.ratingservice.request.RatingRequest;
import link.attech.ratingservice.response.BaseResponse;
import link.attech.ratingservice.response.UserRatings;
import link.attech.ratingservice.service.RateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
public class RateServiceImpl implements RateService {
    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public BaseResponse<UserRatings> getRatings(String userId) {
        List<Rating> ratings = ratingRepository.findAllByUserId(userId);
        UserRatings ratings1 =  UserRatings.builder()
                .ratings(ratings)
                .build();
        return new BaseResponse<>(HttpStatus.OK, "User ratings", ratings1);
    }

    @Override
    public BaseResponse<Rating> addRating(RatingRequest ratingRequest) {
        Rating rating = Rating.builder()
                .userId(ratingRequest.getUserId())
                .movieId(ratingRequest.getMovieId())
                .rate(ratingRequest.getRate())
                .build();
        ratingRepository.save(rating);
        return new BaseResponse<>(HttpStatus.OK, "Rating saved", rating);
    }
}
