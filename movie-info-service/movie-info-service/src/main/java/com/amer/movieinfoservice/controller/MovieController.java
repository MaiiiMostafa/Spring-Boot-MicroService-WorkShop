package com.amer.movieinfoservice.controller;

import com.amer.movieinfoservice.dal.model.Movie;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @RequestMapping("/{movieId}")
    public Movie getMovieDetails(@PathVariable Integer movieId)
    {
        return new Movie(movieId , "Transformers" , "Good Movie");
    }

}
