package com.makrosoft.movies.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.makrosoft.movies.model.Copy;
import com.makrosoft.movies.model.CopyStatusEnum;
import com.makrosoft.movies.model.Movie;

public interface ICopyRepository extends JpaRepository<Copy, Integer> {

    Optional<Copy> findFirstByMovieAndStatus(Movie movie, CopyStatusEnum status);

}
