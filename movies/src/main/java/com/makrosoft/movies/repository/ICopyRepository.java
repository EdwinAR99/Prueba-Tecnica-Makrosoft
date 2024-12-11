package com.makrosoft.movies.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.makrosoft.movies.model.Copy;
import com.makrosoft.movies.model.CopyStatusEnum;
import com.makrosoft.movies.model.Movie;

/**
 * Repository for accessing copy data.
 */
@Repository
public interface ICopyRepository extends JpaRepository<Copy, Integer> {

    /**
     * Finds the first available copy of a specific movie with the given status.
     *
     * @param movie  The movie entity.
     * @param status The status of the copy (e.g., DISPONIBLE, ALQUILADA).
     * @return An Optional containing the copy if found.
     */
    Optional<Copy> findFirstByMovieAndStatus(Movie movie, CopyStatusEnum status);

}
