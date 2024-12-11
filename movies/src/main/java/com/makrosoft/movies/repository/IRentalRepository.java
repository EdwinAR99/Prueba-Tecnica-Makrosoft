package com.makrosoft.movies.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.makrosoft.movies.model.Rental;

/**
 * Repository for accessing rental data.
 */
@Repository
public interface IRentalRepository extends JpaRepository<Rental, Integer> {

    /**
     * Finds the latest rental record for a specific copy ID.
     *
     * @param copyId The ID of the copy.
     * @return An Optional containing the latest rental if found.
     */
    Optional<Rental> findTopByCopyIdOrderByIdDesc(Integer copyId);

}
