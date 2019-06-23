package com.amer.moviecatalogservice.controller;

import com.amer.moviecatalogservice.dal.model.CatalogItem;
import com.amer.moviecatalogservice.dal.model.Movie;
import com.amer.moviecatalogservice.dal.model.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class CatalogController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient.Builder builder;

    //private WebClient.Builder builder =  WebClient.builder();

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {


        List<Rating> ratings = Arrays.asList(
            new Rating(5678 , 4),
            new Rating(1234 , 3)
        );

        return ratings.stream().map(rating -> {
           // Movie movie = restTemplate.getForObject("http://localhost:8082/movies/"+rating.getMovieId() , Movie.class);

            Movie movie = builder.build().get().uri("http://localhost:8082/movies/"+rating.getMovieId())
                    .retrieve().bodyToMono(Movie.class)
                    .block();

            return new CatalogItem(movie.getName() , movie.getDescription() , rating.getRating());
        }).collect(Collectors.toList());

    }
}