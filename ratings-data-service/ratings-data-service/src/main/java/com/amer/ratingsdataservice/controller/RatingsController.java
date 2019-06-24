package com.amer.ratingsdataservice.controller;


import com.amer.ratingsdataservice.dal.model.Rating;
import com.amer.ratingsdataservice.dal.model.UserRating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ratingsdata")
public class RatingsController {


    @RequestMapping("/{movieId}")
    public Rating getMovieRatings(@PathVariable Integer movieId)
    {
        return new Rating(movieId , 4);
    }

    @RequestMapping("/users/{userId}")
    public UserRating getUserRatings(@PathVariable Integer userId)
    {
        List<Rating> ratings = Arrays.asList(
                new Rating(5678 , 4),
                new Rating(1234 , 3)
        );

        UserRating userRating = new UserRating();
        userRating.setUserRatings(ratings);

        return userRating;

    }

}
