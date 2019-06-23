package com.amer.ratingsdataservice.controller;


import com.amer.ratingsdataservice.dal.model.Rating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ratingsdata")
public class RatingsController {


    @RequestMapping("/{movieId}")
    public Rating getMovieRatings(@PathVariable Integer movieId)
    {
        return new Rating(movieId , 4);

    }


}
