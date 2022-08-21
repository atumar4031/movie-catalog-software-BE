package link.attech.ratingservice.controller;


import link.attech.ratingservice.entity.Rating;
import link.attech.ratingservice.request.RatingRequest;
import link.attech.ratingservice.response.BaseResponse;
import link.attech.ratingservice.response.UserRatings;
import link.attech.ratingservice.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/rating")
public class RatingController {

    //TODO: get user id from login context

    @Autowired
    private RateService rateService;

    @GetMapping("/{userId}")
    public UserRatings getRatings(@PathVariable String userId){
        return rateService.getRatings(userId).getResponse();
    }

    @PostMapping
    public BaseResponse<Rating> addRating(@RequestBody RatingRequest ratingRequest){
        return rateService.addRating(ratingRequest);
    }

}
