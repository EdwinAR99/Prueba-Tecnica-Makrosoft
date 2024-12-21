package com.makrosoft.movies.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.makrosoft.movies.service.IMovieService;
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

    private final IMovieService movieService;

    /**
     * Endpoint to get all movies with optional filters.
     *
     * @param pageNumber The page number to retrieve.
     * @param pageSize The size of each page.
     * @param id The ID of the movie to filter by (optional).
     * @param name The name of the movie to filter by (optional).
     * @param description The description of the movie to filter by (optional).
     * @return A paginated response containing movies.
     */
    @GetMapping
    public ResponseEntity<Response<PageableResponse<Object>>> getAllMovies(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String description) {
        return new ResponseEntity<>(this.movieService.getAllMovies(pageNumber, pageSize, id, name, description), HttpStatus.OK);
    }

    /**
     * Endpoint to search for movies by name or description and retrieve available copies.
     *
     * @param query The search query (name or description).
     * @return A list of available movies with the count of free copies.
     */
    @GetMapping("/search")
    public ResponseEntity<Response<PageableResponse<Object>>> searchAvailableMovies(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam("query") String query) {
        return new ResponseEntity<>(this.movieService.searchAvailableMovies(pageNumber, pageSize, query), HttpStatus.OK);
    }

    /**
     * Endpoint to get the movie rental report as a list.
     *
     * @param pageable the pagination information
     * @return List of MovieReportDto
     */
    @GetMapping("/report")
    public ResponseEntity<Response<PageableResponse<Object>>> getMovieReport(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize) {
        return new ResponseEntity<>(this.movieService.getMovieReport(pageNumber, pageSize), HttpStatus.OK);
    }

}
