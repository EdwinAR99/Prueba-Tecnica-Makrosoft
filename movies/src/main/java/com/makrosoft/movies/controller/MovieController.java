package com.makrosoft.movies.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.makrosoft.movies.service.impl.MovieServiceImpl;
import com.makrosoft.movies.util.response.PageableResponse;
import com.makrosoft.movies.util.response.Response;

import lombok.RequiredArgsConstructor;

/**
 * Controller for managing movie-related endpoints.
 */
@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
@Validated
public class MovieController {

    private final MovieServiceImpl movieService;

    /**
     * Endpoint to search for movies by name or description and retrieve available copies.
     *
     * @param query The search query (name or description).
     * @return A list of available movies with the count of free copies.
     */
    @GetMapping("/search")
    public ResponseEntity<Response<PageableResponse<Object>>> searchAvailableMovies(
            @RequestParam(defaultValue = "pageNumber") int pageNumber,
            @RequestParam(defaultValue = "pageSize") int pageSize,
            @RequestParam("query") String query) {
        return new ResponseEntity<>(this.movieService.searchAvailableMovies(pageNumber, pageSize, query), HttpStatus.OK);
    }

}
