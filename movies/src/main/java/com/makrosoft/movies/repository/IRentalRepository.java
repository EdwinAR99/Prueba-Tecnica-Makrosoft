package com.makrosoft.movies.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.makrosoft.movies.model.Rental;

public interface IRentalRepository extends JpaRepository<Rental, Integer> {

}
