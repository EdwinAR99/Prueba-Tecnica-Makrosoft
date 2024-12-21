package com.makrosoft.movies.service;

import com.makrosoft.movies.util.response.PageableResponse;
import com.makrosoft.movies.util.response.Response;

/**
 * Service interface for handling movie-related operations.
 */
public interface IMovieService {

    /**
     * Retrieves all movies with pagination and filters.
     *
     * @param pageNumber The page number to retrieve.
     * @param pageSize   The size of each page.
     * @param id         The ID of the movie to filter by (optional).
     * @param name       The name of the movie to filter by (optional).
     * @param description The description of the movie to filter by (optional).
     * @return A paginated response containing movies.
     */
    Response<PageableResponse<Object>> getAllMovies(int pageNumber, int pageSize, Integer id, String name, String description);

    /**
     * Searches for movies by name or description, retrieving their available copies with pagination.
     *
     * @param pageNumber The page number to retrieve.
     * @param pageSize   The size of each page.
     * @param query      The search query (searches in name or description).
     * @return A paginated response containing movies and their available copies.
     */
    Response<PageableResponse<Object>> searchAvailableMovies(int pageNumber, int pageSize, String query);

    /**
     * Retrieves a rental report for all movies, including rental statistics and revenue.
     *
     * @param pageNumber The page number to retrieve.
     * @param pageSize   The size of each page.
     * @return A paginated response containing the movie rental report data.
     */
    Response<PageableResponse<Object>> getMovieReport(int pageNumber, int pageSize);
}
