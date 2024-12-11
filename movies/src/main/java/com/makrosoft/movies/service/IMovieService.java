package com.makrosoft.movies.service;

import com.makrosoft.movies.util.response.PageableResponse;
import com.makrosoft.movies.util.response.Response;

/**
 * Service for movie-related business logic.
 */
public interface IMovieService {

    /**
     * Searches for movies by name or description and retrieves available copies with pagination.
     *
     * @param query      The search query (name or description).
     * @param pageNumber The page number.
     * @param pageSize   The size of the page.
     * @return A paginated response containing movies with their available copies.
     */
    Response<PageableResponse<Object>> searchAvailableMovies(int pageNumber, int pageSize, String query);

    /**
     * Get the movie rental report.
     * 
     * @param pageNumber The page number.
     * @param pageSize   The size of the page.
     * @return List of MovieReportDto
     */
    Response<PageableResponse<Object>> getMovieReport(int pageNumber, int pageSize);
}
