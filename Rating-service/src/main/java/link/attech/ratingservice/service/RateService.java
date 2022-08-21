package link.attech.ratingservice.service;

import link.attech.ratingservice.entity.Rating;
import link.attech.ratingservice.request.RatingRequest;
import link.attech.ratingservice.response.BaseResponse;
import link.attech.ratingservice.response.UserRatings;

public interface RateService {
    BaseResponse<UserRatings> getRatings(String userId);
    BaseResponse<Rating> addRating(RatingRequest ratingRequest);
}
