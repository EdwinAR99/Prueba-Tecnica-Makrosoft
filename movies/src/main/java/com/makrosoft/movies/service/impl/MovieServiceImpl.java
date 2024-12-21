package com.makrosoft.movies.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.makrosoft.movies.exception.BusinessRuleException;
import com.makrosoft.movies.mapper.IMovieMapper;
import com.makrosoft.movies.model.Movie;
import com.makrosoft.movies.repository.IMovieRepository;
import com.makrosoft.movies.service.IMovieService;
import com.makrosoft.movies.util.response.PageableResponse;
import com.makrosoft.movies.util.response.Response;
import com.makrosoft.movies.util.response.handler.ResponseHandler;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements IMovieService {

    private final IMovieRepository movieRepository;
    private final IMovieMapper movieMapper;

    /**
     * @see IMovieService#getAllMovies(int, int, Long, String, String)
     */
    @Override
    public Response<PageableResponse<Object>> getAllMovies(int pageNumber, int pageSize, Integer id, String name,
            String description) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);

        Page<Movie> page;

        if (id != null) {
            page = movieRepository.findById(id, pageRequest);
        } else if (name != null && description != null) {
            page = movieRepository.findByNameAndDescription(name, description, pageRequest);
        } else if (name != null) {
            page = movieRepository.findByName(name, pageRequest);
        } else if (description != null) {
            page = movieRepository.findByDescription(description, pageRequest);
        } else {
            page = movieRepository.findAll(pageRequest);
        }

        if (page.getTotalElements() == 0)
            throw new BusinessRuleException("movie.search.not.found");

        List<Object> movieList = page.get().map(
            this.movieMapper::toDtoFind
        ).collect(Collectors.toList());

        PageableResponse<Object> response = PageableResponse.builder()
                .data(movieList)
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .build();

        return new ResponseHandler<>(200, "Movies found successfully", "", response).getResponse();
    }

    /**
     * @see IMovieService#searchAvailableMovies(Integer, Integer, String)
     */
    @Override
    public Response<PageableResponse<Object>> searchAvailableMovies(int pageNumber, int pageSize, String query) {

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);

        // Fetch movies using the repository
        Page<Movie> page = movieRepository.searchMoviesByNameOrDescription(query, pageRequest);
        if (page.getTotalElements() == 0)
            throw new BusinessRuleException("movie.search.name.not.found");

        // Map the entities to DTOs
        List<Object> movieList = page.get().map(
            this.movieMapper::toDtoAvailable
        ).collect(Collectors.toList());

        // Build a paginated response
        PageableResponse<Object> response = PageableResponse.builder()
                .data(movieList)
                .pageNo(page.getNumber())
                .pageSize(page.getSize())
                .totalElements(page.getTotalElements())
                .last(page.isLast())
                .build();

        // Return the response using a handler
        return new ResponseHandler<>(200, "Movies found successfully", "", response).getResponse();
    }

    /**
     * @see IMovieService#getMovieReport(Integer, Integer)
     */
    @Override
    public Response<PageableResponse<Object>> getMovieReport(int pageNumber, int pageSize) {

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);

        // Fetch data from repository
        Page<Object[]> page = movieRepository.findMovieReport(pageRequest);
        if (page.getTotalElements() == 0)
            throw new BusinessRuleException("movie.rental.not.exists");

        // Map results to DTO and collect to list
        List<Object> movieList = page.get().map(
                record -> movieMapper.toDtoReport(
                        (String) record[0],
                        (String) record[1],
                        ((Number) record[2]).longValue(),
                        ((Number) record[3]).doubleValue()))
                .collect(Collectors.toList());

        // Build a paginated response
        PageableResponse<Object> response = PageableResponse.builder()
                .data(movieList)
                .pageNo(page.getNumber())
                .pageSize(page.getSize())
                .totalElements(page.getTotalElements())
                .last(page.isLast())
                .build();

        // Return the response using a handler
        return new ResponseHandler<>(200, "Movies found successfully", "", response).getResponse();
    }

}
