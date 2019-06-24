package com.amer.moviecatalogservice.controller;

import com.amer.moviecatalogservice.dal.model.CatalogItem;
import com.amer.moviecatalogservice.dal.model.Movie;
import com.amer.moviecatalogservice.dal.model.Rating;
import com.amer.moviecatalogservice.dal.model.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@EnableEurekaClient
@RequestMapping("/catalog")
public class CatalogController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient.Builder builder;


    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {


        UserRating userRating = restTemplate.getForObject("http://ratings-data-service/ratingsdata/users/"+userId , UserRating.class);

        return userRating.getUserRatings().stream().map(rating -> {
            Movie movie = restTemplate.getForObject("http://movie-info-service/movies/"+rating.getMovieId() , Movie.class);

//            Movie movie = builder.build().get().uri("http://localhost:8082/movies/"+rating.getMovieId())
//                    .retrieve().bodyToMono(Movie.class)
//                    .block();

            return new CatalogItem(movie.getName() , movie.getDescription() , rating.getRating());
        }).collect(Collectors.toList());

    }
}