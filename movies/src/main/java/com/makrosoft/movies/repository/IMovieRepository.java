package com.makrosoft.movies.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.makrosoft.movies.model.Movie;

/**
 * Repository for accessing movie data.
 */
@Repository
public interface IMovieRepository  extends JpaRepository<Movie, Integer> {

    /**
     * Searches movies by name or description containing the query string (case-insensitive).
     *
     * @param query The search query.
     * @param Pageable The object to save result
     * @return A list of matching movies.
     */
    @Query("SELECT m FROM Movie m WHERE LOWER(m.name) LIKE LOWER(CONCAT('%', :query, '%')) OR " + "LOWER(m.description) LIKE LOWER(CONCAT('%', :query, '%'))")
    Page<Movie> searchMoviesByNameOrDescription(String query, Pageable pageable);

}
