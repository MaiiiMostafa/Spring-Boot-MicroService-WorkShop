package com.amer.moviecatalogservice.controller;

import com.amer.moviecatalogservice.dal.model.CatalogItem;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/catalog")
public class CatalogController {


    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
        return Collections.singletonList(
                new CatalogItem("3 idiots", "Good Moview", 4)
        );
    }
}